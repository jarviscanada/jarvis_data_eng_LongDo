package ca.jrvs.practice.codingChallenge;

public class RotateString {

  /**
   * Big O: O(n^2) Justification: contains have runtime of O(nm) but in this situation we have the
   * same string so it will be O(n^2)
   */
  public boolean rotateString(String s, String goal) {
    return s.length() == goal.length() && (s + s).contains(goal);
  }
}
