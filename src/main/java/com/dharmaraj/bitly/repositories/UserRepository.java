package com.dharmaraj.bitly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dharmaraj.bitly.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
