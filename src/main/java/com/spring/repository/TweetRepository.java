package com.spring.repository;

import com.spring.domain.Tweet;

public interface TweetRepository {

    Iterable<Tweet> allTweets();

}
