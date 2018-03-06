package edu.sjsu.cmpe275.aop;

import java.io.IOException;

public class TweetServiceImpl implements TweetService {

    /***
     * Following is a dummy implementation.
     * You can tweak the implementation to suit your need, but this file is NOT part of the submission.
     */
    int i = 0;

    public void tweet(String user, String message) throws IllegalArgumentException, IOException {
        System.out.printf("User %s tweeted message: %s\n", user, message);
    }

    public void follow(String follower, String followee) throws IOException {
        if (i >= 2 && i < 6) {
            System.out.println(i);
            throw new IOException();
        } else {
            System.out.printf("User %s followed user %s \n", follower, followee);
        }
        i++;
    }

    public void block(String user, String follower) throws IOException {
        System.out.printf("User %s blocked user %s \n", user, follower);
    }

}
