package com.epam;

import com.epam.ioc.ApplicationContext;
import com.epam.ioc.Config;
import com.epam.ioc.Context;
import com.epam.repository.InMemTweetRepositoryImpl;
import com.epam.repository.TweetRepository;
import com.epam.service.SimpleTweetService;
import com.epam.service.TweetService;
import com.epam.ioc.JavaMapConfig;

import java.util.HashMap;
import java.util.Map;

public class IoCRunner {
    public static void main(String[] args) {

        Map<String, Map<String, Object>> beanDescriptions =
                new HashMap<String, Map<String, Object>>(){{
                    put("tweetRepository",
                            new HashMap<String, Object>() {{
                                put("type", InMemTweetRepositoryImpl.class);
                                put("isPrototype", false);
                    }});
                    put("tweetService", new HashMap<String, Object>() {{
                        put("type", SimpleTweetService.class);
                        put("isPrototype", false);
                    }});
                }};

        Config config = new JavaMapConfig(beanDescriptions);
        Context context = new ApplicationContext(config);

        TweetRepository tweetRepository = (TweetRepository) context.getBean("tweetRepository");
        TweetService tweetService = (TweetService) context.getBean("tweetService");

        System.out.println(tweetRepository.allTweets());
        System.out.println(tweetService.allTweets());


    }
}
