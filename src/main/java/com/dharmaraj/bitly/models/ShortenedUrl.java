package com.dharmaraj.bitly.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class ShortenedUrl extends BaseModel {

    private String originalUrl;
    private String shortUrl;
    private long expiresAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "shortenedUrl", cascade = CascadeType.ALL)
    private List<UrlAccessLog> accessLogs;
}

/**
 * shortenedUrl : User
 * 1 : 1
 * M : 1
 */