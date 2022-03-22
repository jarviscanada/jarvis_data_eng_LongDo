package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;

public class TwitterService implements Service {

  private CrdDao dao;

  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    //Business logic:
    //e.g. Text length, lat/lon range, id format
    validatePostTweet(tweet);

    return (Tweet) dao.create(tweet);

  }

  void validatePostTweet(Tweet tweet) {
    int tweetTextLength = tweet.getText().length();
    Coordinates coordinates = tweet.getCoordinates();
    if (tweetTextLength == 0 || tweetTextLength > 140) {
      throw new IllegalArgumentException("Invalid tweet length");
    }

    if (coordinates != null && coordinates.getCoordinates() != null && (
        coordinates.getCoordinates().get(0) > 180.0 || coordinates.getCoordinates().get(0) < -180.0
            || coordinates.getCoordinates().get(1) > 90.0
            || coordinates.getCoordinates().get(1) < -90.0)) {
      throw new IllegalArgumentException("Invalid coordinates");
    }


  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    return null;
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    return null;
  }
}

