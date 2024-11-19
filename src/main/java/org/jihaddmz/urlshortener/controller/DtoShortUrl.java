package org.jihaddmz.urlshortener.controller;

import lombok.Data;
import lombok.val;

@Data
public class DtoShortUrl {

    private String shortUrl;

    public DtoShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
