package edu.sjsu.cmpe275.aop;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class TweetTest {

    /***
     * These are dummy test cases. You may add test cases based on your own need.
     */

    @Autowired
    TweetService tweetService;
    @Autowired
    TweetStats stats;

    @Before
    public void prepare() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
        tweetService = (TweetService) ctx.getBean("tweetService");
        stats = (TweetStats) ctx.getBean("tweetStats");
    }

    @Test
    public void testOne() {
        try {
            tweetService.tweet("foo", "barbar");
            Assert.assertEquals("Check length of longest tweet", 6, stats.getLengthOfLongestTweetAttempted());
        } catch (IOException ex) {
            Assert.fail("fail");

        }
    }

    @Test
    public void testTwo() {
        try {
            tweetService.follow("Alice ", "Bob");
            tweetService.follow("Carl", "Bob");
            tweetService.follow("Bob", "Alice");
            Assert.assertEquals("Check most followed user", "Alice", stats.getMostFollowedUser());
        } catch (IOException ex) {
            Assert.fail("fail");
        }
    }

    @Test
    public void testThree() {
        try {
            tweetService.tweet("Alice", "sadbasdash asjdkaskjd askldaskd");

            Assert.assertEquals("Check most followed user", "Alice", stats.getMostProductiveUser());
        } catch (IOException ex) {
            Assert.fail("fail");

        }
    }

    @After
    public void clear() {
        stats.resetStatsAndSystem();
    }
}