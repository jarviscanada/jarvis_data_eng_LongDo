package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class TwitterService implements Service {

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  /**
   * Function to validate ID
   *
   * @param id
   */
  void validateID(String id) {
    try {
      Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid id format", e);
    }
  }

  /**
   * Function to validate post Tweet
   *
   * @param tweet
   */
  private void validatePostTweet(Tweet tweet) {
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


  /**
   * Function to filter the fields not in the list to null
   *
   * @param tweet
   * @param fields
   * @return Tweet
   */
  private Tweet filterFields(Tweet tweet, String[] fields) {
    Class tweetClass = Tweet.class;
    Field fieldList[] = tweetClass.getDeclaredFields();
    Tweet filteredTweet = tweet;

    HashSet<String> fieldsToAdd = new HashSet<>();
    for (String field : fields) {
      fieldsToAdd.add(field);
    }

    try {
      for (Field field : fieldList) {
        if (!fieldsToAdd.contains(field.getName())) {
          char[] getAccess = field.getName().toCharArray();
          getAccess[0] = Character.toUpperCase(getAccess[0]);
          String setMethodName = "set" + String.valueOf(getAccess);

          Method set = tweetClass.getMethod(setMethodName, field.getType());
          Object value = null;
          set.invoke(filteredTweet, value);
        }
      }
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException("Problem filtering tweet object according to args", e);
    }
    return filteredTweet;
  }


  /**
   * Create a tweet
   *
   * @param tweet
   * @return tweet
   */
  @Override
  public Tweet postTweet(Tweet tweet) {
    //Business logic:
    //e.g. Text length, lat/lon range, id format
    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet);
  }


  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateID(id);
    Tweet tweet = (Tweet) dao.findById(id);
    if (fields != null) {
      tweet = filterFields(tweet, fields);
    }
    return tweet;
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> tweets = new ArrayList<>();
    for (String id : ids) {
      validateID(id);
      Tweet deletedTweet = (Tweet) dao.deleteById(id);
      tweets.add(deletedTweet);
    }
    return tweets;
  }
}

