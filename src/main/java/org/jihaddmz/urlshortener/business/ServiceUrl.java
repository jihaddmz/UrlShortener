package org.jihaddmz.urlshortener.business;

import org.jihaddmz.urlshortener.RepoUrl;
import org.jihaddmz.urlshortener.model.ModelUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceUrl {

    @Autowired
    private RepoUrl repoUrl;

    public String saveShortUrl(String originalUrl) {
        String shortUrl = UUID.randomUUID().toString().substring(0, 6); // Generate a random 6-character string

        if (repoUrl.findByShortenedUrl(shortUrl).isEmpty()) {
            repoUrl.save(new ModelUrl(originalUrl, shortUrl));
        }

        return shortUrl;
    }

    public String findOriginalUrl(String shortUrl) {
        Optional<ModelUrl> modelUrl = repoUrl.findByShortenedUrl(shortUrl);

        if (modelUrl.isEmpty()) {
            throw new NoSuchElementException("No such short url: " + shortUrl);
        }

        return modelUrl.get().getOriginalUrl();
    }

    public List<ModelUrl> findAll() {
        return repoUrl.findAll();
    }
}
