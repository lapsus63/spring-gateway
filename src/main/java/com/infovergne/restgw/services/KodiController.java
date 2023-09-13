package com.infovergne.restgw.services;

import com.infovergne.restgw.api.HttpClientApi;
import com.infovergne.restgw.config.KodiConfig;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.infovergne.restgw.config.KodiConfig.*;

@RestController
public class KodiController {

    private final KodiConfig conf;

    private final HttpClientApi httpClientApi;

    public KodiController(KodiConfig conf, HttpClientApi httpClientApi) {
        this.conf = conf;
        this.httpClientApi = httpClientApi;
    }

    @GetMapping("/playpause")           public ResponseEntity<String> playpause(@RequestParam(value = "token") String token) {  return doRequest(token, PAYLOAD_PLAYPAUSE);  }
    @GetMapping("/stop")                public ResponseEntity<String> stop(@RequestParam(value = "token") String token) {       return doRequest(token, PAYLOAD_STOP);  }
    @GetMapping("/volup")               public ResponseEntity<String> volUp(@RequestParam(value = "token") String token) {      return doRequest(token, PAYLOAD_VOL_UP);     }
    @GetMapping("/voldown")             public ResponseEntity<String> volDown(@RequestParam(value = "token") String token) {    return doRequest(token, PAYLOAD_VOL_DOWN);   }

    @GetMapping("/radio-campus")        public ResponseEntity<String> radioCampus(@RequestParam(value = "token") String token) {        return doRequest(token, PAYLOAD_RADIO_CAMPUS);       }
    @GetMapping("/radio-nova")          public ResponseEntity<String> radioNova(@RequestParam(value = "token") String token) {          return doRequest(token, PAYLOAD_RADIO_NOVA);         }
    @GetMapping("/radio-classique")     public ResponseEntity<String> radioClassique(@RequestParam(value = "token") String token) {     return doRequest(token, PAYLOAD_RADIO_CLASSIQUE);    }
    @GetMapping("/radio-france-inter")  public ResponseEntity<String> radioFranceInter(@RequestParam(value = "token") String token) {   return doRequest(token, PAYLOAD_RADIO_FRANCE_INTER); }

    @GetMapping("/playlist-tous")       public ResponseEntity<String> playlistTous(@RequestParam(value = "token") String token) {       return doRequest(token, PAYLOAD_PLAYLIST_TOUS); }
    @GetMapping("/playlist-albums")     public ResponseEntity<String> playlistAlbums(@RequestParam(value = "token") String token) {     return doRequest(token, PAYLOAD_PLAYLIST_ALBUMS); }
    @GetMapping("/playlist-generiques") public ResponseEntity<String> playlistGeneriques(@RequestParam(value = "token") String token) { return doRequest(token, PAYLOAD_PLAYLIST_OST); }
    @GetMapping("/playlist-classical")  public ResponseEntity<String> playlistClassical(@RequestParam(value = "token") String token) {  return doRequest(token, PAYLOAD_PLAYLIST_CLASSICAL); }
    @GetMapping("/playlist-disney")     public ResponseEntity<String> playlistDisney(@RequestParam(value = "token") String token) {     return doRequest(token, PAYLOAD_PLAYLIST_DISNEY);    }
    @GetMapping("/playlist-victor")     public ResponseEntity<String> playlistVictor(@RequestParam(value = "token") String token) {     return doRequest(token, PAYLOAD_PLAYLIST_VICTOR);    }
    @GetMapping("/playlist-chanson")    public ResponseEntity<String> playlistChanson(@RequestParam(value = "token") String token) {    return doRequest(token, PAYLOAD_PLAYLIST_CHANSON);   }
    @GetMapping("/playlist-world")      public ResponseEntity<String> playlistWorld(@RequestParam(value = "token") String token) {      return doRequest(token, PAYLOAD_PLAYLIST_WORLD);     }


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
