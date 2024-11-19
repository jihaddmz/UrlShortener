package org.jihaddmz.urlshortener.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.jihaddmz.urlshortener.business.ServiceUrl;
import org.jihaddmz.urlshortener.model.ModelUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ControllerUrl {

    @Autowired
    private ServiceUrl serviceUrl;

    @Autowired
    private ServletContext servletContext;

    @Operation(description = "Creating a short url for the provided long one")
    @PostMapping("/api/url")
    public DtoShortUrl createShortUrl(@RequestParam String longUrl, HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + servletContext.getContextPath();
        return new DtoShortUrl(baseUrl + "/" + serviceUrl.saveShortUrl(longUrl));
    }

    @GetMapping("/{shorturl}")
    public ResponseEntity<Void> getOriginalUrl(@PathVariable String shorturl) {
        String originalUrl = serviceUrl.findOriginalUrl(shorturl);
        return ResponseEntity.status(HttpStatus.FOUND) // HTTP 302 for redirection
                .header("Location", originalUrl)
                .build();
    }

    @GetMapping("/api/url")
    public List<ModelUrl> findAll() {
        return serviceUrl.findAll();
    }
}
