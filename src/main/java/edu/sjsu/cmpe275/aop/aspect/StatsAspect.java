package edu.sjsu.cmpe275.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import edu.sjsu.cmpe275.aop.TweetStatsImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@Aspect
@Order(0)
public class StatsAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advices as needed.
     */

    @Autowired TweetStatsImpl tweetStats;

    @Before("execution(public void edu.sjsu.cmpe275.aop.TweetService.tweet(..))")
    public void setTweetInMap(JoinPoint joinPoint) {
        System.out.println("Before Tweeting compare and set length of longest tweet");

        String tweetMessage = joinPoint.getArgs()[1].toString();
        if (tweetMessage.length() > tweetStats.getLengthOfLongestTweetAttempted()) {
            tweetStats.setLongest_tweet_attempted(tweetMessage.length());
        }
    }

    @AfterReturning("execution(public void edu.sjsu.cmpe275.aop.TweetService.tweet(..))")
    public void setTweetLengthInMap(JoinPoint joinPoint) {
        System.out.println("After successful tweet add the length of tweet in map");

        String user = joinPoint.getArgs()[0].toString();
        String tweetMessage = joinPoint.getArgs()[1].toString();

        if (TweetStatsImpl.getUserTweetMap().containsKey(user))
            TweetStatsImpl.getUserTweetMap().put(user, (tweetMessage.length() + TweetStatsImpl.getUserTweetMap().get(user)));
        else
            TweetStatsImpl.getUserTweetMap().put(user, tweetMessage.length());
    }

//	@Before("execution(public void edu.sjsu.cmpe275.aop.TweetStatsImpl.getLengthOfLongestTweetAttempted(..))")
//	public void getLongestTweetInMap(JoinPoint joinPoint) {
//        System.out.println("Before Tweeting set the value in map");
//
//        String user = joinPoint.getArgs()[0].toString();
//        String tweetMessage = joinPoint.getArgs()[1].toString();
//
//        TweetStatsImpl.getUserTweetMap().put(user, tweetMessage.length());
//	}

    @AfterReturning("execution(public void edu.sjsu.cmpe275.aop.TweetService.follow(..))")
    public void setFollowerInMap(JoinPoint joinPoint) {
        System.out.println("After following set the follower in a map");
        String follower = joinPoint.getArgs()[0].toString();
        String followee = joinPoint.getArgs()[1].toString();

        if(TweetStatsImpl.getUserFollowerMap().containsKey(followee))
            TweetStatsImpl.getUserFollowerMap().put(followee, (1 + TweetStatsImpl.getUserFollowerMap().get(followee)));
        else
            TweetStatsImpl.getUserFollowerMap().put(followee, 1);

    }

    @AfterReturning("execution(public void edu.sjsu.cmpe275.aop.TweetService.block(..))")
    public void setBlocksInMap(JoinPoint joinPoint) {
        System.out.println("After blocking set the count of blocks in a map");
        String user = joinPoint.getArgs()[0].toString();
        String follower = joinPoint.getArgs()[1].toString();

        //user blocks a follower.
        if (TweetStatsImpl.getUserBlockMap().containsKey(follower))
            TweetStatsImpl.getUserBlockMap().put(follower, (TweetStatsImpl.getUserBlockMap().get(follower) + 1));
        else
            TweetStatsImpl.getUserBlockMap().put(follower, 1);
    }

//    @Before("execution(public String edu.sjsu.cmpe275.aop.TweetStatsImpl.getMostFollowedUser(..))")
//    public void getMostFollowedUser(JoinPoint joinPoint) {
//        System.out.println(" find the most followed user ");
//
//        int max_follow_count = 0;
//        ArrayList<String> users = new ArrayList<String>();
//
//        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserFollowerMap().entrySet()) {
//            if(entry.getValue() > max_follow_count){
//                max_follow_count = entry.getValue();
//            }
//        }
//
//        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserFollowerMap().entrySet()) {
//            if(entry.getValue() == max_follow_count){
//                users.add(entry.getKey());
//            }
//        }
//
//        Collections.sort(users);
//
//        tweetStats.setMost_followed_user(users.get(0));
//    }
//
//    @Before("execution(public String edu.sjsu.cmpe275.aop.TweetStatsImpl.getMostBlockedFollower(..))")
//    public void getMostBlockedFollower(JoinPoint joinPoint) {
//        System.out.println(" find the most followed user ");
//
//        int max_block_count = 0;
//        ArrayList<String> users = new ArrayList<String>();
//
//        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserBlockMap().entrySet()) {
//            if(entry.getValue() > max_block_count){
//                max_block_count = entry.getValue();
//            }
//        }
//
//        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserBlockMap().entrySet()) {
//            if(entry.getValue() == max_block_count){
//                users.add(entry.getKey());
//            }
//        }
//
//        Collections.sort(users);
//
//        tweetStats.setMost_blocked_user(users.get(0));
//    }
//
//    @Before("execution(public String edu.sjsu.cmpe275.aop.TweetStatsImpl.getMostProductiveUser(..))")
//    public void getMostProductiveUsers(JoinPoint joinPoint) {
//        System.out.println(" find the most productive user ");
//
//        int max_tweetLens_count = 0;
//        ArrayList<String> users = new ArrayList<String>();
//
//        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserTweetMap().entrySet()) {
//            if(entry.getValue() > max_tweetLens_count){
//                max_tweetLens_count = entry.getValue();
//            }
//        }
//
//        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserTweetMap().entrySet()) {
//            if(entry.getValue() == max_tweetLens_count){
//                users.add(entry.getKey());
//            }
//        }
//
//        Collections.sort(users);
//
//        tweetStats.setMost_productive_user(users.get(0));
//    }

}
