package com.dharmaraj.bitly.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "users")
public class User extends BaseModel {

    private String name;
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private UserPlan userPlan;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Optional
    private List<ShortenedUrl> shortenedUrls;
}
