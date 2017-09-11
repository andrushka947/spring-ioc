package ua.allugard.repository;

import ua.allugard.domain.Tweet;

public interface TweetRepository {

    Iterable<Tweet> allTweets();

}
