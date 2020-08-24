package org.tmichael.music_timeline.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.tmichael.music_timeline.config.SpotifyProperties;

@Slf4j
@Component
public class SpotifyAuthorizer {

    private final SpotifyProperties properties;
    private final RestTemplate restTemplate;


    @Autowired
    public SpotifyAuthorizer(SpotifyProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    public String authorize() {
        ResponseEntity<String> response = restTemplate.getForEntity(buildAuthorizeUrl(), String.class);
        String body = response.getBody();
        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Error authorizing with Spotify: " + response.getStatusCodeValue());
            log.error("Error body: " + body);
        }
        log.info("Authorize response body: " + body);

        return body;
    }

    private String buildAuthorizeUrl() {
        return UriComponentsBuilder.fromHttpUrl(properties.getBaseUrl() + "/authorize")
                .queryParam("client_id", properties.getClientId())
                .queryParam("response_type", "code")
                .queryParam("redirect_uri", properties.getRedirectUri())
//                .queryParam("state", "auth")
                .queryParam("show_dialog", false)
                .queryParam("scope", buildScopeString())
                .toUriString();
    }

    private String buildScopeString() {
        StringBuilder builder = new StringBuilder();
        for (String scope : properties.getScopes()) {
            builder.append(scope).append(" ");
        }

        return builder.toString().trim();
    }
}
