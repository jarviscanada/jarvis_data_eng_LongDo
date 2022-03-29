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
import org.junit.Test;

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

  @Test
  public void firstPostTweetSuccess() {
    String[] args = {"post", "test tweet", "1:-1"};
    Double expectedLon = Double.parseDouble(args[2].split(":")[1]);
    Double expectedLat = Double.parseDouble(args[2].split(":")[0]);
    Tweet actual = twitterController.postTweet(args);
    Assert.assertNotNull(actual);
    Assert.assertEquals(args[1], actual.getText());
    Assert.assertEquals(expectedLon, actual.getCoordinates().getCoordinates().get(0));
    Assert.assertEquals(expectedLat, actual.getCoordinates().getCoordinates().get(1));
    id = actual.getId_str();
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

  @Test
  public void secondShowTweetSuccessAllFields() {
    String[] args = {"show", id};
    Tweet actual = twitterController.showTweet(args);
    Assert.assertNotNull(actual);
    Assert.assertNotNull(actual.getCreated_at());
    Assert.assertNotNull(actual.getId());
    Assert.assertNotNull(actual.getId_str());
    Assert.assertNotNull(actual.getText());
    Assert.assertNotNull(actual.getEntities());
    Assert. assertNotNull(actual.getCoordinates());
    Assert.assertNotNull(actual.getRetweet_count());
    Assert. assertNotNull(actual.getFavorite_count());
    Assert.assertNotNull(actual.getFavorited());
    Assert.assertNotNull(actual.getRetweeted());
    Assert.assertEquals("test tweet", actual.getText());
    Assert.assertEquals(id, actual.getId_str());
  }

  @Test
  public void secondShowTweetSuccessSomeFields() {
    String[] args = {"show", id, "id_str,text"};
    Tweet actual = twitterController.showTweet(args);
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.getCreated_at());
    Assert.assertNull(actual.getId());
    Assert.assertNotNull(actual.getId_str());
    Assert.assertNotNull(actual.getText());
    Assert.assertNull(actual.getEntities());
    Assert.assertNull(actual.getCoordinates());
    Assert.assertNull(actual.getRetweet_count());
    Assert.assertNull(actual.getFavorite_count());
    Assert.assertNull(actual.getFavorited());
    Assert.assertNull(actual.getRetweeted());
    Assert.assertEquals("test tweet", actual.getText());
    Assert.assertEquals(id, actual.getId_str());
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

  @Test
  public void thirdDeleteTweetSuccess() {
    String[] args = {"delete", id};
    List<Tweet> actual = twitterController.deleteTweet(args);
    Assert.assertNotNull(actual.get(0));
    Assert.assertNotNull(actual.get(0).getCreated_at());
    Assert.assertNotNull(actual.get(0).getId());
    Assert.assertNotNull(actual.get(0).getId_str());
    Assert.assertNotNull(actual.get(0).getText());
    Assert.assertNotNull(actual.get(0).getEntities());
    Assert.assertNotNull(actual.get(0).getCoordinates());
    Assert.assertNotNull(actual.get(0).getRetweet_count());
    Assert.assertNotNull(actual.get(0).getFavorite_count());
    Assert.assertNotNull(actual.get(0).getFavorited());
    Assert.assertNotNull(actual.get(0).getRetweeted());
    Assert.assertEquals("test tweet", actual.get(0).getText());
    Assert.assertEquals(id, actual.get(0).getId_str());
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
