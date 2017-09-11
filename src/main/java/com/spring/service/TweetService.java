package com.spring.service;

import com.spring.domain.Tweet;

public interface TweetService {
    Iterable<Tweet> allTweets();
}
