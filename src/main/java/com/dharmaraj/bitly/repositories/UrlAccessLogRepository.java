package com.dharmaraj.bitly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dharmaraj.bitly.models.UrlAccessLog;

@Repository
public interface UrlAccessLogRepository extends JpaRepository<UrlAccessLog, Integer> {

}
