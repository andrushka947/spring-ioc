package com.epam.service;

import com.epam.domain.Tweet;

public interface TweetService {
    Iterable<Tweet> allTweets();
}
