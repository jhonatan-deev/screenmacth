package com.example.screenmatch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OmdbConfig {

    @Value("${omdb.api.url}")
    private String url;

    @Value("${omdb.api.key}")
    private String apiKey;

    public String getUrl() {
        return url;
    }

    public String getApiKey() {
        return apiKey;
    }
}
