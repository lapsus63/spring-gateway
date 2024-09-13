package com.infovergne.restgw.kodi.services;

import com.infovergne.restgw.api.HttpClientApi;
import com.infovergne.restgw.kodi.config.KodiConfig;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConditionalOnProperty(name = "project.kodi.token")
public class KodiController {
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
            httpClientApi.post(kodiUrl, payload);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
