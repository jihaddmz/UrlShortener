package org.jihaddmz.urlshortener.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "urls")
public class ModelUrl {

    @Id
    private String id;

    private String shortenedUrl;
    private String originalUrl;

    public ModelUrl(String originalUrl, String shortenedUrl) {
        this.originalUrl = originalUrl;
        this.shortenedUrl = shortenedUrl;
    }

    public ModelUrl() {
    }
}
