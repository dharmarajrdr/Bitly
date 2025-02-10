package com.dharmaraj.bitly.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dharmaraj.bitly.models.ShortenedUrl;

@Repository
public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, Integer>  {

    public Optional<ShortenedUrl> findByShortUrl(String shortUrl);
}
