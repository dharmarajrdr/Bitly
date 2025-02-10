package com.dharmaraj.bitly.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class UrlAccessLog extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shortened_url_id", nullable = false)
    private ShortenedUrl shortenedUrl;
    
    private long accessedAt;
}

/**
 * urlAccessLog : ShortenedUrl
 * 1 : 1
 * M : 1
 */