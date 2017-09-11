package com.epam.service;

import com.epam.domain.Tweet;
import com.epam.repository.TweetRepository;

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
