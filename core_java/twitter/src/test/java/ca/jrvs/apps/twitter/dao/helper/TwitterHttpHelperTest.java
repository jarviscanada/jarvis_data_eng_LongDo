package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class TwitterHttpHelperTest {

  private HttpHelper httpHelper;

  @Before
  public void setUp() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    this.httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
  }

  @Test
  public void httpPost() throws Exception {
    HttpResponse response = httpHelper.httpPost(
        new URI("https://api.twitter.com/1.1/statuses/update.json?status=httpPost_test&lat=1.5&long=12.4"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void httpGet() throws Exception {
    HttpResponse response = httpHelper.httpGet(
        new URI("https://api.twitter.com/1.1/statuses/show.json?id=1509186492840787970"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

}
