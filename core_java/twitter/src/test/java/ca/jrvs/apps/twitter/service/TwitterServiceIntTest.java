package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.JsonUtil;
import ca.jrvs.apps.twitter.dao.helper.TweetUtil;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TwitterServiceIntTest {

  private static TwitterService twitterService;
  private static Map<Integer, String> ids;

  @BeforeClass
  public static void setUp() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + " | " + consumerSecret + " | "
        + accessToken + " | " + tokenSecret);
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,
        accessToken, tokenSecret);

    TwitterDAO twitterDAO = new TwitterDAO(httpHelper);
    twitterService = new TwitterService(twitterDAO);
    ids = new HashMap<>();
  }

  @Test
  public void firstPostTweetSuccessTweetUtil() {
    String text = "testing tweet";
    Double lon = 1.0;
    Double lat = -1.0;
    Tweet actual = twitterService.postTweet(TweetUtil.buildTweet(text, lon, lat));
    Assert.assertNotNull(actual);
    Assert.assertNotNull(actual.getId_str());
    Assert.assertEquals(text, actual.getText());
    Assert.assertEquals(lon, actual.getCoordinates().getCoordinates().get(0));
    Assert.assertEquals(lat, actual.getCoordinates().getCoordinates().get(1));
    ids.put(1, actual.getId_str());
  }

  @Test
  public void firstPostTweetSuccessManual() {
    String text = "testing tweet manually";
    Tweet tweet = new Tweet();
    tweet.setText(text);
    Tweet actual = twitterService.postTweet(tweet);
    Assert.assertNotNull(actual);
    Assert.assertNotNull(actual.getId_str());
    Assert.assertNull(actual.getCoordinates());
    Assert.assertEquals(text, actual.getText());
    ids.put(2, actual.getId_str());

  }

  @Test(expected = IllegalArgumentException.class)
  public void firstPostTweetFailureNoText() {
    Double lon = 1.0;
    Double lat = -1.0;
    twitterService.postTweet(TweetUtil.buildTweet("", lon, lat));
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstPostTweetFailureLongText() {
    String longText = "This is some ridiculously long text that I am coming up with here."
        + "It really has no business being this long other than to test my app when handling"
        + " ridiculously long texts such as the one I am coming up with here. I think this "
        + "should be enough to test what I want to test but I have to be sure so I should keep"
        + " writing... I am not going to count if this is longer than the 140 characters I need"
        + " to test my code so I shall keep writing. However, I am pretty sure I have hit my "
        + "required amount so I shall stop. Please be enough.........";
    Double lon = 1.0;
    Double lat = -1.0;
    twitterService.postTweet(TweetUtil.buildTweet(longText, lon, lat));
  }


  @Test(expected = IllegalArgumentException.class)
  public void firstPostTweetFailureBadLongitude() {
    Double lon = 1000.0;
    Double lat = -1.0;
    twitterService.postTweet(TweetUtil.buildTweet("", lon, lat));
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstPostTweetFailureBadLatitude() {
    Double lon = 1.0;
    Double lat = -1000.0;
    twitterService.postTweet(TweetUtil.buildTweet("", lon, lat));
  }

  @Test
  public void secondShowTweetSuccessAllFields() throws Exception {
    String[] fields = {};
    Tweet actual1 = twitterService.showTweet(ids.get(1), fields);
    Tweet actual2 = twitterService.showTweet(ids.get(2), fields);

    Assert.assertNotNull(actual1);
    Assert.assertNotNull(actual1.getCreated_at());
    Assert.assertNotNull(actual1.getId());
    Assert.assertNotNull(actual1.getId_str());
    Assert.assertEquals(ids.get(1), actual1.getId_str());
    Assert.assertNotNull(actual1.getText());
    Assert.assertNotNull(actual1.getEntities());
    Assert.assertNotNull(actual1.getCoordinates());
    Assert.assertNotNull(actual1.getRetweet_count());
    Assert.assertNotNull(actual1.getFavorite_count());
    Assert.assertNotNull(actual1.getFavorited());
    Assert.assertNotNull(actual1.getRetweeted());

    Assert.assertNotNull(actual2);
    Assert.assertNotNull(actual2.getCreated_at());
    Assert.assertNotNull(actual2.getId());
    Assert.assertNotNull(actual2.getId_str());
    Assert.assertEquals(ids.get(2), actual2.getId_str());
    Assert.assertNotNull(actual2.getText());
    Assert.assertNotNull(actual2.getEntities());
    Assert.assertNull(actual2.getCoordinates());
    Assert.assertNotNull(actual2.getRetweet_count());
    Assert.assertNotNull(actual2.getFavorite_count());
    Assert.assertNotNull(actual2.getFavorited());
    Assert.assertNotNull(actual2.getRetweeted());

    System.out.println(JsonUtil.toPrettyJson(actual1));
    System.out.println(JsonUtil.toPrettyJson(actual2));
  }

  @Test
  public void secondShowTweetSuccessSelectFields() throws Exception {
    String[] fields = {"id_str", "text"};
    Tweet actual1 = twitterService.showTweet(ids.get(1), fields);
    Tweet actual2 = twitterService.showTweet(ids.get(2), fields);

    Assert.assertNotNull(actual1);
    Assert.assertNull(actual1.getCreated_at());
    Assert.assertNull(actual1.getId());
    Assert.assertNotNull(actual1.getId_str());
    Assert.assertEquals(ids.get(1), actual1.getId_str());
    Assert.assertNotNull(actual1.getText());
    Assert.assertNull(actual1.getEntities());
    Assert.assertNull(actual1.getCoordinates());
    Assert.assertNull(actual1.getRetweet_count());
    Assert.assertNull(actual1.getFavorite_count());
    Assert.assertNull(actual1.getFavorited());
    Assert.assertNull(actual1.getRetweeted());

    Assert.assertNotNull(actual2);
    Assert.assertNull(actual2.getCreated_at());
    Assert.assertNull(actual2.getId());
    Assert.assertNotNull(actual2.getId_str());
    Assert.assertEquals(ids.get(2), actual2.getId_str());
    Assert.assertNotNull(actual2.getText());
    Assert.assertNull(actual2.getEntities());
    Assert.assertNull(actual2.getCoordinates());
    Assert.assertNull(actual2.getRetweet_count());
    Assert.assertNull(actual2.getFavorite_count());
    Assert.assertNull(actual2.getFavorited());
    Assert.assertNull(actual2.getRetweeted());

    System.out.println(JsonUtil.toPrettyJson(actual1));
    System.out.println(JsonUtil.toPrettyJson(actual2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void secondShowTweetFailureBadId() {
    String[] fields = {};
    twitterService.showTweet("someid", fields);
  }

  @Test
  public void thirdDeleteTweetSuccess() {
    String[] idsArray = ids.values().toArray(new String[0]);
    List<Tweet> tweetsDeleted = twitterService.deleteTweets(idsArray);
    Assert.assertNotNull(tweetsDeleted.get(0));
    Assert.assertEquals(ids.get(1), tweetsDeleted.get(0).getId_str());
    Assert.assertNotNull(tweetsDeleted.get(0).getText());
    System.out.println(tweetsDeleted.get(0).getText());
    Assert.assertNotNull(tweetsDeleted.get(1));
    Assert.assertEquals(ids.get(2), tweetsDeleted.get(1).getId_str());
    Assert.assertNotNull(tweetsDeleted.get(1).getText());
    System.out.println(tweetsDeleted.get(1).getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void thirdDeleteTweetFailure() {
    String[] idsArray = {"some id", "1234"};
    twitterService.deleteTweets(idsArray);
  }


}
