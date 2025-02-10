package com.dharmaraj.bitly.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dharmaraj.bitly.exceptions.UrlNotFoundException;
import com.dharmaraj.bitly.exceptions.UserNotFoundException;
import com.dharmaraj.bitly.factory.UserPlanFactory;
import com.dharmaraj.bitly.models.ShortenedUrl;
import com.dharmaraj.bitly.models.UrlAccessLog;
import com.dharmaraj.bitly.models.User;
import com.dharmaraj.bitly.repositories.ShortenedUrlRepository;
import com.dharmaraj.bitly.repositories.UrlAccessLogRepository;
import com.dharmaraj.bitly.repositories.UserRepository;
import com.dharmaraj.bitly.strategy.UserPlanStrategy;
import com.dharmaraj.bitly.utils.ShortUrlGenerator;

@Service
public class UrlServiceImpl implements UrlService {

    private UserRepository UserRepository;
    private ShortenedUrlRepository shortenedUrlRepository;
    private UrlAccessLogRepository urlAccessLogRepository;

    @Autowired
    public UrlServiceImpl(UserRepository userRepository, ShortenedUrlRepository shortenedUrlRepository, UrlAccessLogRepository urlAccessLogRepository) {
        this.UserRepository = userRepository;
        this.shortenedUrlRepository = shortenedUrlRepository;
        this.urlAccessLogRepository = urlAccessLogRepository;
    }

    private long daysToMills(long days) {
        return days * 24L * 60 * 60 * 1000;
    }

    private boolean isExpired(long time1, long time2) {
        return time1 < time2;
    }

    @Override
    public ShortenedUrl shortenUrl(String originalUrl, int userId) throws UserNotFoundException {

        Optional<User> optionalUser = this.UserRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = optionalUser.get();

        UserPlanStrategy userPlanStrategy = UserPlanFactory.getStrategy(user.getUserPlan());

        long currentTimeMillis = System.currentTimeMillis();
        long expiresAt = daysToMills(userPlanStrategy.getTimeToLiveDays()) + currentTimeMillis;

        String shortUrl = ShortUrlGenerator.generateShortUrl();
        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setShortUrl(shortUrl);
        shortenedUrl.setOriginalUrl(originalUrl);
        shortenedUrl.setUser(user);
        shortenedUrl.setExpiresAt(expiresAt);

        this.shortenedUrlRepository.save(shortenedUrl);
        return shortenedUrl;
    }

    @Override
    public String resolveShortenedUrl(String shortUrl) throws UrlNotFoundException {
        
        Optional<ShortenedUrl> optionalShortenedUrl = this.shortenedUrlRepository.findByShortUrl(shortUrl);
        if(optionalShortenedUrl.isEmpty()) {
            throw new UrlNotFoundException("Url not found");
        }

        ShortenedUrl shortenedUrl = optionalShortenedUrl.get();

        long currentTimeMillis = System.currentTimeMillis();

        if(isExpired(shortenedUrl.getExpiresAt(), currentTimeMillis)) {
            throw new UrlNotFoundException("Url expired");
        }
        
        UrlAccessLog urlAccessLog = new UrlAccessLog();
        urlAccessLog.setShortenedUrl(shortenedUrl);
        urlAccessLog.setAccessedAt(currentTimeMillis);
        this.urlAccessLogRepository.save(urlAccessLog);

        return shortenedUrl.getOriginalUrl();
    }
    
}
