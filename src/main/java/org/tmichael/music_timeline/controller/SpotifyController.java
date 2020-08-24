package org.tmichael.music_timeline.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tmichael.music_timeline.service.SpotifyAuthorizer;

@Slf4j
@RestController
@RequestMapping("spotify")
public class SpotifyController {

    private final SpotifyAuthorizer authorizer;


    @Autowired
    public SpotifyController(SpotifyAuthorizer authorizer) {
        this.authorizer = authorizer;
    }

    @GetMapping(value = "authorize", produces = MediaType.TEXT_HTML_VALUE)
    public String authorize() {
        return authorizer.authorize();
    }

    @GetMapping("callback")
    public void callback(@RequestParam("code") String code) {
        log.info("callback, code: " + code);
    }
}
