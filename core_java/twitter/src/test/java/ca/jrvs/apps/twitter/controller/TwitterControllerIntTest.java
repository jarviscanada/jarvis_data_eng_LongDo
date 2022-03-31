package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.controler.TwitterController;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TwitterControllerIntTest {

  private static TwitterController twitterController;
  private static String id;
  @BeforeClass
  public static void setUp(){
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);
    TwitterDAO twitterDao = new TwitterDAO(httpHelper);
    TwitterService twitterService = new TwitterService(twitterDao);
    twitterController = new TwitterController(twitterService);
  }




  @Test(expected = IllegalArgumentException.class)
  public void firstPostTweetFailureWrongNumArgs() {
    String[] args = {"test tweet"};
    twitterController.postTweet(args);
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstPostTweetFailureWrongNumCoords() {
    String[] args = {"post", "test tweet", "1:1:1"};
    twitterController.postTweet(args);
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstPostTweetFailureInvalidCoords() {
    String[] args = {"post", "test tweet", "-10000:10"};
    twitterController.postTweet(args);
  }





  @Test(expected = IllegalArgumentException.class)
  public void secondShowTweetFailureWrongNumArgs() {
    String[] args = {"show"};
    twitterController.showTweet(args);
  }

  @Test(expected = IllegalArgumentException.class)
  public void secondShowTweetFailureInvalidId() {
    String[] args = {"show", "some_id"};
    twitterController.showTweet(args);
  }

  @Test(expected = IllegalArgumentException.class)
  public void secondShowTweetFailureInvalidField() {
    String[] args = {"show", id, "some_field"};
    twitterController.showTweet(args);
  }



  @Test(expected = IllegalArgumentException.class)
  public void thirdDeleteTweetFailureWrongNumArgs() {
    String[] args = {"delete"};
    twitterController.deleteTweet(args);
  }

  @Test(expected = IllegalArgumentException.class)
  public void thirdDeleteTweetFailureInvalidId() {
    String[] args = {"delete", "some_id"};
    twitterController.deleteTweet(args);
  }

}
