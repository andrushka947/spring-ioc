package ua.allugard.service;

import ua.allugard.domain.Tweet;
import ua.allugard.repository.TweetRepository;

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
