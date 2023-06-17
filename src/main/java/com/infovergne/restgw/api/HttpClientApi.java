package com.infovergne.restgw.api;

import org.json.JSONArray;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * https://www.baeldung.com/java-httpclient-post
 */
public class HttpClientApi {

    public static void post(String serviceUrl, JSONArray payload) throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serviceUrl))
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString(), StandardCharsets.UTF_8))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
