package ua.allugard.service;

import ua.allugard.domain.Tweet;

public interface TweetService {
    Iterable<Tweet> allTweets();
}
