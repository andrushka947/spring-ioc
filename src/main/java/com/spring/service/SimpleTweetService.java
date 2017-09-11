package com.spring.service;

import com.spring.domain.Tweet;
import com.spring.repository.TweetRepository;

public class SimpleTweetService implements TweetService {

    private final TweetRepository tweetRepository;

    public SimpleTweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Iterable<Tweet> allTweets() {
        return tweetRepository.allTweets();
    }
}
