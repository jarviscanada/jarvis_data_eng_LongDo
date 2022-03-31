package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controler.Controller;
import ca.jrvs.apps.twitter.controler.TwitterController;
import ca.jrvs.apps.twitter.dao.TwitterDAO;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.JsonUtil;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIApp {

  public static final String USAGE = "USAGE: TwitterCLIApp post|show|delete [options]";
  private Controller twitterController;

  @Autowired
  public TwitterCLIApp(Controller twitterController) {
    this.twitterController = twitterController;
  }

  public void run(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException(USAGE);
    }

    switch (args[0].toLowerCase(Locale.ROOT)) {
      case "post":
        printTweet(twitterController.postTweet(args));
        break;
      case "show":
        printTweet(twitterController.showTweet(args));
        break;
      case "delete":
        twitterController.deleteTweet(args).forEach(this::printTweet);
      default:
        throw new IllegalArgumentException(USAGE);
    }
  }

  private void printTweet(Tweet tweet) {
    try {
      System.out.println(JsonUtil.toPrettyJson(tweet));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert tweet object to string", e);
    }
  }

  public static void main(String[] args) {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    TwitterDAO dao = new TwitterDAO(httpHelper);
    TwitterService service = new TwitterService(dao);
    TwitterController controller = new TwitterController(service);
    TwitterCLIApp twitterCLIApp = new TwitterCLIApp(controller);

    twitterCLIApp.run(args);
  }

}
