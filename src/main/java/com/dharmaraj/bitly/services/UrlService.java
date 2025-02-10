package com.dharmaraj.bitly.services;

import com.dharmaraj.bitly.exceptions.UrlNotFoundException;
import com.dharmaraj.bitly.exceptions.UserNotFoundException;
import com.dharmaraj.bitly.models.ShortenedUrl;

public interface UrlService {

    public ShortenedUrl shortenUrl(String url, int userId) throws UserNotFoundException;
    public String resolveShortenedUrl(String shortUrl) throws UrlNotFoundException;
}
