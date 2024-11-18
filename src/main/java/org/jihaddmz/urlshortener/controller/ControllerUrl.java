package org.jihaddmz.urlshortener.controller;

import org.jihaddmz.urlshortener.business.ServiceUrl;
import org.jihaddmz.urlshortener.model.ModelUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/url")
public class ControllerUrl {

    @Autowired
    private ServiceUrl serviceUrl;

    @PostMapping
    public String createShortUrl(@RequestParam String longUrl) {
        return serviceUrl.saveShortUrl(longUrl);
    }

    @GetMapping("/{shorturl}")
    public ResponseEntity<Void> getOriginalUrl(@PathVariable String shorturl) {
        String originalUrl = serviceUrl.findOriginalUrl(shorturl);
        return ResponseEntity.status(HttpStatus.FOUND) // HTTP 302 for redirection
                .header("Location", originalUrl)
                .build();
    }

    @GetMapping
    public List<ModelUrl> findAll() {
        return serviceUrl.findAll();
    }
}
