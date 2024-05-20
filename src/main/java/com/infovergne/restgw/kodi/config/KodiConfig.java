package com.infovergne.restgw.kodi.config;

import jakarta.annotation.PostConstruct;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
@ConditionalOnProperty(name = "project.kodi.token")
public class KodiConfig {

    public static final String TEMPLATE_URL = "%URL%";

    @Value("${project.kodi.token}")
    private String token;

    @Value("${project.kodi.url}")
    private String url;

    @Value("classpath:kodi_payloads.json")
    private Resource resourceFile;

    private JSONObject kodiPayloads;

    @PostConstruct
    public void init() throws Exception {
        kodiPayloads = new JSONObject(Files.readString(Paths.get(resourceFile.getURI())));
    }

    public String getKodiUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public JSONArray getArray(String key) {
        return kodiPayloads.getJSONArray(key);
    }

    public JSONArray getRadio(String key) {
        String radioTemplate = kodiPayloads.getJSONArray("radio-template").toString();
        String radioUrl = kodiPayloads.getJSONObject("radios").getString(key);
        return new JSONArray(radioTemplate.replace(TEMPLATE_URL, radioUrl));
    }

    public JSONArray getPlaylist(String key) {
        String playlistTemplate = kodiPayloads.getJSONArray("playlist-template").toString();
        String playlistUrl = kodiPayloads.getJSONObject("playlists").getString(key);
        return new JSONArray(playlistTemplate.replace(TEMPLATE_URL, playlistUrl));
    }

}
