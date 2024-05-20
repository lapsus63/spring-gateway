package com.infovergne.restgw.solaredge;

import com.infovergne.restgw.api.HttpClientApi;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;

@Component
@ConditionalOnProperty(name = "project.solaredge.token")
public class SolarEdgeScheduler {

    public static final String TEMPLATE_SITE_ID = "%SITE_ID%";

    private static final Log logger = LogFactory.getLog(SolarEdgeScheduler.class);

    private final HttpClientApi httpClient;

    @Value("${project.solaredge.site_id}")
    private String siteId;

    @Value("${project.solaredge.token}")
    private String token;

    @Value("${project.solaredge.url.root}")
    private String urlRoot;

    @Value("${project.solaredge.url.currentPowerFlow}")
    private String urlCurrentPowerFlow;

    @Value("${project.solaredge.currentPowerFlow.triggerOffThreshold}")
    private BigDecimal currentPowerFlowTriggerOffThreshold;

    @Value("${project.solaredge.currentPowerFlow.triggerOffUrl}")
    private String currentPowerFlowTriggerOffUrl;

    @Value("${project.solaredge.currentPowerFlow.triggerOnThreshold}")
    private BigDecimal currentPowerFlowTriggerOnThreshold;

    @Value("${project.solaredge.currentPowerFlow.triggerOnUrl}")
    private String currentPowerFlowTriggerOnUrl;

    private int currentPowerFlowActivation = 0;

    public SolarEdgeScheduler(HttpClientApi httpClient) {
        this.httpClient = httpClient;
    }

    @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.MINUTES)
    public void loadCurrentPowerFlow() throws Exception {

        String urlTemplate = String.format("%s%s", urlRoot, urlCurrentPowerFlow);
        String serviceUrl = urlTemplate.replace(TEMPLATE_SITE_ID, siteId);
        String[] headers = ArrayUtils.toArray("X-API-Key", token);
        HttpResponse<String> response = httpClient.get(serviceUrl, headers);
        if (HttpStatus.resolve(response.statusCode()).is2xxSuccessful()) {
            updateCurrentMetrics(new JSONObject(response.body()).getJSONObject("siteCurrentPowerFlow"));
        }
    }

    private void updateCurrentMetrics(JSONObject jsonObject) throws Exception {
        BigDecimal pvPower = jsonObject.getJSONObject("PV").optBigDecimal("currentPower", BigDecimal.ZERO);
        BigDecimal loadPower = jsonObject.getJSONObject("LOAD").optBigDecimal("currentPower", BigDecimal.ZERO);
        BigDecimal gridPower = jsonObject.getJSONObject("GRID").optBigDecimal("currentPower", BigDecimal.ZERO);
        logger.info(String.format("Solar input is %s kW ; Home consumes %s kW ; Resell value is %s kW", pvPower.setScale(2).toPlainString(), loadPower.setScale(2).toPlainString(), gridPower.setScale(2).toPlainString()));

        if (gridPower.compareTo(currentPowerFlowTriggerOffThreshold) < 0 && currentPowerFlowActivation >= 0) {
            logger.info("Current grid value is too low ; triggering the switch off");
            HttpResponse<String> response = httpClient.get(currentPowerFlowTriggerOffUrl);
            currentPowerFlowActivation = -1;

        } else if (gridPower.compareTo(currentPowerFlowTriggerOnThreshold) > 0 && currentPowerFlowActivation <= 0) {
            logger.info("Current grid value is high ; triggering the switch on");
            HttpResponse<String> response = httpClient.get(currentPowerFlowTriggerOnUrl);
            currentPowerFlowActivation = 1;
        }
    }
}
