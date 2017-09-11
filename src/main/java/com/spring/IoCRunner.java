package com.spring;

import com.spring.ioc.ApplicationContext;
import com.spring.ioc.Config;
import com.spring.ioc.Context;
import com.spring.repository.InMemTweetRepositoryImpl;
import com.spring.repository.TweetRepository;
import com.spring.service.SimpleTweetService;
import com.spring.service.TweetService;
import com.spring.ioc.JavaMapConfig;

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
