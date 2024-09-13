package com.infovergne.restgw.solaredge.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "project.solaredge.token")
public class SolarEdgeConfig {

    @Value("${project.solaredge.token}")
    private String token;

    public String getToken() {
        return token;
    }
}
