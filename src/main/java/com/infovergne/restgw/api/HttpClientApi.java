package com.infovergne.restgw.api;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    private static final Log logger = LogFactory.getLog(HttpClientApi.class);

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

    public HttpResponse<String> get(String serviceUrl, String... headers) throws Exception {

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.of(connectTimeout, ChronoUnit.SECONDS))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serviceUrl))
                .headers(ArrayUtils.addAll(headers, ArrayUtils.toArray("User-Agent", "Java")))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (!HttpStatus.resolve(response.statusCode()).is2xxSuccessful()) {
            logger.error(String.format("Unsuccessful API access. Error %s: %s", response.statusCode(), response.body()));
        }
        return response;
    }

}
