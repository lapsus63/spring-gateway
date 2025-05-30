package com.infovergne.restgw.kodi.services;

import com.infovergne.restgw.api.HttpClientApi;
import com.infovergne.restgw.kodi.config.KodiConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@ConditionalOnProperty(name = "project.kodi.token")
public class KodiController {

    private static final Log logger = LogFactory.getLog(KodiController.class);

    private final KodiConfig conf;


    private final HttpClientApi httpClientApi;

    public KodiController(KodiConfig conf, HttpClientApi httpClientApi) {
        this.conf = conf;
        this.httpClientApi = httpClientApi;
    }

    @GetMapping("/kodi/control-{kodiKey}")
    public ResponseEntity<String> triggerControl(@RequestParam(value = "token") String token, @PathVariable("kodiKey") String kodiKey) {
        return doRequest(token, conf.getArray(kodiKey));
    }

    @GetMapping("/kodi/radio-{kodiKey}")
    public ResponseEntity<String> triggerRadio(@RequestParam(value = "token") String token, @PathVariable("kodiKey") String kodiKey) {
        return doRequest(token, conf.getRadio(kodiKey));
    }

    @GetMapping("/kodi/playlist-{kodiKey}")
    public ResponseEntity<String> triggerPlayList(@RequestParam(value = "token") String token, @PathVariable("kodiKey") String kodiKey) {
        return doRequest(token, conf.getPlaylist(kodiKey));
    }

    private ResponseEntity<String> doRequest(String inputToken, JSONArray payload) {

        try {
            String kodiUrl = conf.getKodiUrl();
            String appToken   = conf.getToken();
            if (!StringUtils.equals(appToken, inputToken)) {
                return ResponseEntity.notFound().build();
            }
            HttpResponse<String> response = httpClientApi.post(kodiUrl, payload);
            String responseBody = response.body();
            if (!HttpStatus.resolve(response.statusCode()).is2xxSuccessful() || StringUtils.trimToEmpty(responseBody).toLowerCase().contains("\"error\":")) {
                logger.error(responseBody);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
            } else {
                logger.info(responseBody);
                return ResponseEntity.ok(responseBody);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
