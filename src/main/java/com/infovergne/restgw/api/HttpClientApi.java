package com.infovergne.restgw.api;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * https://www.baeldung.com/java-httpclient-post
 */
@Component
public class HttpClientApi {

    @Value("${project.http.connect-timeout}")
    private Long connectTimeout;

    public void post(String serviceUrl, JSONArray payload) throws Exception {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.of(connectTimeout, ChronoUnit.SECONDS))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serviceUrl))
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString(), StandardCharsets.UTF_8))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
