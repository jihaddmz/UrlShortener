package org.jihaddmz.urlshortener;

import org.jihaddmz.urlshortener.model.ModelUrl;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RepoUrl extends MongoRepository<ModelUrl, String> {

    Optional<ModelUrl> findByShortenedUrl(String shortenedUrl);

    Optional<ModelUrl> findByOriginalUrl(String originalUrl);
}
