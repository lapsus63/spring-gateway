package com.infovergne.restgw.solaredge.services;


import com.infovergne.restgw.api.HttpClientApi;
import com.infovergne.restgw.solaredge.SolarEdgeScheduler;
import com.infovergne.restgw.solaredge.config.SolarEdgeConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ConditionalOnProperty(name = "project.solaredge.token")
public class SolarEdgeController {

    private static final Log logger = LogFactory.getLog(SolarEdgeController.class);

    private final SolarEdgeConfig conf;

    private final HttpClientApi httpClientApi;

    public SolarEdgeController(SolarEdgeConfig conf, HttpClientApi httpClientApi) {
        this.conf = conf;
        this.httpClientApi = httpClientApi;
    }

    @GetMapping("/solaredge/on")
    public ResponseEntity<String> solarEdgeActivation(@RequestParam(value = "token") String token) {
        ResponseEntity<String> response = prepareRequest(token);
        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Solar Edge Service UP");
            SolarEdgeScheduler.SOLAR_EDGE_ACTIVE = true;
        }
        return response;
    }

    @GetMapping("/solaredge/status")
    public ResponseEntity<String> solarEdgeStatus(@RequestParam(value = "token") String token) {
        ResponseEntity<String> response = prepareRequest(token);
        if (response.getStatusCode().is2xxSuccessful()) {
            response = ResponseEntity.ok(""+SolarEdgeScheduler.SOLAR_EDGE_ACTIVE);
        }
        return response;
    }


    @GetMapping("/solaredge/off")
    public ResponseEntity<String> solarEdgeDesactivation(@RequestParam(value = "token") String token) {
        ResponseEntity<String> response = prepareRequest(token);
        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("Solar Edge Service DOWN");
            SolarEdgeScheduler.SOLAR_EDGE_ACTIVE = false;
        }
        return response;
    }

    private ResponseEntity<String> prepareRequest(String inputToken) {

        try {
            String appToken   = conf.getToken();
            if (!StringUtils.equals(appToken, inputToken)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
