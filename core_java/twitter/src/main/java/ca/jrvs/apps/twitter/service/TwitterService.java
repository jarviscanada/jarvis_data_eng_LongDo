package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
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
