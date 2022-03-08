package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RotateStringTest {
  private RotateString rotateString;

  @Before
  public void setUp() {
    rotateString = new RotateString();
  }

  @Test
  public void rotateString(){
    Assert.assertTrue(rotateString.rotateString("abcde", "bcdea"));
    Assert.assertTrue(rotateString.rotateString("", ""));
    Assert.assertFalse(rotateString.rotateString("abcde", "abced"));
    Assert.assertFalse(rotateString.rotateString("abcde", ""));
    Assert.assertFalse(rotateString.rotateString("", "abcde"));
  }
}
