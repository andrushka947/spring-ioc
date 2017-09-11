package com.spring.repository;

import com.spring.domain.Tweet;

import java.util.Arrays;
import java.util.List;

public class InMemTweetRepositoryImpl implements TweetRepository {

    private List<Tweet> tweets;

    {
        tweets = Arrays.asList(
                new Tweet(1L, "First msg", null),
                new Tweet(2L, "Second msg", null)
        );
    }

    @Override
    public Iterable<Tweet> allTweets() {
        return tweets;
    }


}
