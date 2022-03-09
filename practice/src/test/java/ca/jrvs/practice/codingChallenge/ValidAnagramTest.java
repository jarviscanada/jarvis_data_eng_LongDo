package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidAnagramTest {

  private ValidAnagram validAnagram;

  @Before
  public void setUp() {
    validAnagram = new ValidAnagram();
  }

  @Test
  public void isAnagramSort() {
    Assert.assertTrue(validAnagram.isAnagramSort("", ""));
    Assert.assertTrue(validAnagram.isAnagramSort("anagram", "nagaram"));
    Assert.assertFalse(validAnagram.isAnagramSort("", "car"));
    Assert.assertFalse(validAnagram.isAnagramSort("rat", ""));
    Assert.assertFalse(validAnagram.isAnagramSort("rat", "car"));
  }

  @Test
  public void isAnagramSeq() {
    Assert.assertTrue(validAnagram.isAnagramSeq("", ""));
    Assert.assertTrue(validAnagram.isAnagramSeq("anagram", "nagaram"));
    Assert.assertFalse(validAnagram.isAnagramSeq("", "car"));
    Assert.assertFalse(validAnagram.isAnagramSeq("rat", ""));
    Assert.assertFalse(validAnagram.isAnagramSeq("rat", "car"));
  }
}
