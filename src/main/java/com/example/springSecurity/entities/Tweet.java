package com.example.springSecurity.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tweetID;
    @ManyToOne
    private User user;
    private String tweetText;

    private Instant createdAt;
}
