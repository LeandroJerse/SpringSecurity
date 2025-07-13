package com.example.springSecurity.repositories;

import com.example.springSecurity.entities.Role;
import com.example.springSecurity.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, UUID> {
}
