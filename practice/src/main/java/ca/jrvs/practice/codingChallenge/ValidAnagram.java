package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {

  /**
   * Big O: O(nlogn)
   */
  public boolean isAnagramSort(String s, String t) {
    char[] sArr = s.toCharArray();
    char[] tArr = t.toCharArray();
    Arrays.sort(sArr);
    Arrays.sort(tArr);
    return Arrays.equals(sArr, tArr);
  }

  /**
   * Big O: O(n)
   */
  public boolean isAnagramSeq(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }
    int[] alphabet = new int[26];

    for (int i = 0; i < s.length(); i++) {
      alphabet[s.charAt(i) - 'a']++;
      alphabet[t.charAt(i) - 'a']--;
    }

    for (int i : alphabet) {
      if (i != 0) {
        return false;
      }
    }

    return true;
  }
}
