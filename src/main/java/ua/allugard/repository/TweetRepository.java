package com.epam.repository;

import com.epam.domain.Tweet;

public interface TweetRepository {

    Iterable<Tweet> allTweets();

}
