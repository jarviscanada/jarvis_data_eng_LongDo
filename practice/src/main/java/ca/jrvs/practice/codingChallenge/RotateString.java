package ca.jrvs.practice.codingChallenge;

public class RotateString {

  public boolean rotateString(String s, String goal) {
    return s.length() == goal.length() && (s + s).contains(goal);
  }
}
