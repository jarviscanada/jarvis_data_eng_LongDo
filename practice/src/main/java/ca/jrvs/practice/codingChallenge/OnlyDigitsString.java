package ca.jrvs.practice.codingChallenge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlyDigitsString {

  public static boolean asciiCheck(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) < 48 || str.charAt(i) > 57) {
        return false;
      }
    }

    return true;
  }


  public static boolean parserCheck(String str) {
    int stringLength = str.length();

    try {

      if (stringLength < 19) {
        Long.valueOf(str);
      } else {

        int lowDelimiter = 0;
        int highDelimiter = 17;

        while (highDelimiter < stringLength) {

          Long.valueOf(str.substring(lowDelimiter, highDelimiter));
          lowDelimiter = highDelimiter;
          highDelimiter = highDelimiter + 18;
        }

        Integer.valueOf(str.substring(lowDelimiter));
      }
    } catch (NumberFormatException e) {
      return false;
    }

    return true;
  }

  public static boolean regexChecker(String str) {

    Pattern pattern = Pattern.compile("\\d*");
    Matcher matcher = pattern.matcher(str);

    return matcher.matches();
  }

}
