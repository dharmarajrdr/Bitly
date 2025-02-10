package com.dharmaraj.bitly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dharmaraj.bitly.dtos.ResolveShortenUrlRequestDto;
import com.dharmaraj.bitly.dtos.ResolveShortenUrlResponseDto;
import com.dharmaraj.bitly.dtos.ResponseStatus;
import com.dharmaraj.bitly.dtos.ShortenUrlRequestDto;
import com.dharmaraj.bitly.dtos.ShortenUrlResponseDto;
import com.dharmaraj.bitly.exceptions.UrlNotFoundException;
import com.dharmaraj.bitly.exceptions.UserNotFoundException;
import com.dharmaraj.bitly.models.ShortenedUrl;
import com.dharmaraj.bitly.services.UrlService;

@Controller
public class UrlController {

    @Autowired
    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    public ShortenUrlResponseDto shortenUrl(ShortenUrlRequestDto requestDto) {
        ShortenUrlResponseDto shortenUrlResponseDto = new ShortenUrlResponseDto();
        try {
            String originalUrl = requestDto.getOriginalUrl();
            int userId = requestDto.getUserId();
            ShortenedUrl shortenedUrl = this.urlService.shortenUrl(originalUrl, userId);
            shortenUrlResponseDto.setShortUrl(shortenedUrl.getShortUrl());
            shortenUrlResponseDto.setExpiresAt(shortenedUrl.getExpiresAt());
            shortenUrlResponseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException e) {
            shortenUrlResponseDto.setStatus(ResponseStatus.FAILURE);
        }
        return shortenUrlResponseDto;
    }

    public ResolveShortenUrlResponseDto resolveShortenedUrl(ResolveShortenUrlRequestDto requestDto) {
        ResolveShortenUrlResponseDto resolveShortenUrlResponseDto = new ResolveShortenUrlResponseDto();
        try {
            String shortenUrl = requestDto.getShortenUrl();
            String originalUrl = this.urlService.resolveShortenedUrl(shortenUrl);
            resolveShortenUrlResponseDto.setOriginalUrl(originalUrl);
            resolveShortenUrlResponseDto.setStatus(ResponseStatus.SUCCESS);
        } catch(UrlNotFoundException e) {
            resolveShortenUrlResponseDto.setStatus(ResponseStatus.FAILURE);
        }
        return resolveShortenUrlResponseDto;
    }
}
