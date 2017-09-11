package ua.allugard;

import ua.allugard.ioc.ApplicationContext;
import ua.allugard.ioc.Config;
import ua.allugard.ioc.Context;
import ua.allugard.ioc.JavaMapConfig;
import ua.allugard.repository.InMemTweetRepositoryImpl;
import ua.allugard.repository.TweetRepository;
import ua.allugard.service.SimpleTweetService;
import ua.allugard.service.TweetService;

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
