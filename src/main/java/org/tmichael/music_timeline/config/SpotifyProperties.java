package org.tmichael.music_timeline.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Configuration
public class SpotifyProperties {

    @Value("${spotify.client-id}")
    private String clientId;
    @Value("${spotify.client-secret}")
    private String clientSecret;
    @Value("${spotify.base-url}")
    private String baseUrl;
    @Value("${spotify.redirect-uri}")
    private String redirectUri;
    @Value("${spotify.scopes}")
    private List<String> scopes;
}
