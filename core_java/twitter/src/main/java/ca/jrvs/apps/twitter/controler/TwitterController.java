package ca.jrvs.apps.twitter.controler;

import ca.jrvs.apps.twitter.dao.helper.TweetUtil;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  public Service service;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }

    String tweet_text = args[1];
    String coords = args[2];
    String[] coordsArray = coords.split(COORD_SEP);
    if (coordsArray.length != 2) {
      throw new IllegalArgumentException("Invalid location format\n"
          + "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }
    Double lat = null;
    Double lon = null;
    try {
      lat = Double.parseDouble(coordsArray[0]);
      lon = Double.parseDouble(coordsArray[1]);

    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid location format", e);
    }

    return service.postTweet(TweetUtil.buildTweet(tweet_text, lon, lat));
  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length != 2 && args.length != 3) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp show \"tweet_id\" \"[field1, field2,...]\"");
    }

    String id = args[1];
    String[] fields = args.length == 2 ? new String[0] : args[2].split(COMMA);

    return service.showTweet(id, fields);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("USAGE: TwitterCLIApp delete \"[id1, id2,...]\"");
    }

    String ids = args[1];
    String[] idsArray = ids.split(COMMA);

    return service.deleteTweets(idsArray);
  }
}
