package edu.sjsu.cmpe275.aop;

import org.springframework.stereotype.Component;

import java.util.*;

@Component("tweetStats")
public class TweetStatsImpl implements TweetStats {

	public static TreeMap<String, Integer> userFollowerMap = new TreeMap<String, Integer>();
	public static TreeMap<String, Integer> userTweetMap = new TreeMap<String, Integer>();
	public static TreeMap<String, Integer> userBlockMap = new TreeMap<String, Integer>();

	private int longest_tweet_attempted = 0;
    private String most_followed_user;
    private String most_productive_user;
    private String most_blocked_user;

	public void setMost_productive_user(String most_productive_user) {
		this.most_productive_user = most_productive_user;
	}

	public void setMost_blocked_user(String most_blocked_user) {
		this.most_blocked_user = most_blocked_user;
	}

	public void setLongest_tweet_attempted(int longest_tweet_attempted) {
		this.longest_tweet_attempted = longest_tweet_attempted;
	}

	public void setMost_followed_user(String most_followed_user) {
		this.most_followed_user = most_followed_user;
	}

	public static TreeMap<String, Integer> getUserFollowerMap() {
		return userFollowerMap;
	}

	public static TreeMap<String, Integer> getUserTweetMap() {
		return userTweetMap;
	}

	public static TreeMap<String, Integer> getUserBlockMap() {
		return userBlockMap;
	}

	public void resetStatsAndSystem() {
		// TODO Auto-generated method stub
		longest_tweet_attempted = 0;
		most_followed_user = null;
		most_blocked_user = null;
		userFollowerMap.clear();
		userTweetMap.clear();
	}

	public int getLengthOfLongestTweetAttempted() {
		// TODO Auto-generated method stub
		return longest_tweet_attempted;
	}

	public String getMostFollowedUser() {
		// TODO Auto-generated method stub

        int max_follow_count = 0;
        ArrayList<String> users = new ArrayList<String>();

        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserFollowerMap().entrySet()) {
            if(entry.getValue() > max_follow_count){
                max_follow_count = entry.getValue();
            }
        }

        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserFollowerMap().entrySet()) {
            if(entry.getValue() == max_follow_count){
                users.add(entry.getKey());
            }
        }

        Collections.sort(users);

        setMost_followed_user(users.get(0));
		return most_followed_user;
	}

	public String getMostProductiveUser() {
		// TODO Auto-generated method stub

        int max_tweetLens_count = 0;
        ArrayList<String> users = new ArrayList<String>();

        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserTweetMap().entrySet()) {
            if(entry.getValue() > max_tweetLens_count){
                max_tweetLens_count = entry.getValue();
            }
        }

        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserTweetMap().entrySet()) {
            if(entry.getValue() == max_tweetLens_count){
                users.add(entry.getKey());
            }
        }

        Collections.sort(users);

        setMost_productive_user(users.get(0));
		return most_productive_user;
	}

	public String getMostBlockedFollower() {
		// TODO Auto-generated method stub

        int max_block_count = 0;
        ArrayList<String> users = new ArrayList<String>();

        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserBlockMap().entrySet()) {
            if(entry.getValue() > max_block_count){
                max_block_count = entry.getValue();
            }
        }

        for (Map.Entry<String, Integer> entry: TweetStatsImpl.getUserBlockMap().entrySet()) {
            if(entry.getValue() == max_block_count){
                users.add(entry.getKey());
            }
        }

        Collections.sort(users);

        setMost_blocked_user(users.get(0));
		return most_blocked_user;
	}
}



