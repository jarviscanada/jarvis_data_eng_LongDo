package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidPalindromeTest {

  private ValidPalindrome validPalindrome;

  @Before
  public void setUp() {
    validPalindrome = new ValidPalindrome();
  }

  @Test
  public void isPalindromePointer() {
    Assert.assertTrue(validPalindrome.isPalindromePointer(""));
    Assert.assertTrue(validPalindrome.isPalindromePointer("mAdaM"));
    Assert.assertTrue(validPalindrome.isPalindromePointer("         1racecar1"));
    Assert.assertTrue(validPalindrome.isPalindromePointer("A man, a plan, a canal: Panama"));
    Assert.assertTrue(validPalindrome.isPalindromePointer(",."));
    Assert.assertFalse(validPalindrome.isPalindromePointer("12   3"));
    Assert.assertFalse(validPalindrome.isPalindromePointer("raceAcar"));
  }


  @Test
  public void isPalidromeRecur() {
    Assert.assertTrue(validPalindrome.isPalidromeRecur(""));
    Assert.assertTrue(validPalindrome.isPalidromeRecur("mAdaM"));
    Assert.assertTrue(validPalindrome.isPalidromeRecur("         1racecar1"));
    Assert.assertTrue(validPalindrome.isPalidromeRecur("A man, a plan, a canal: Panama"));
    Assert.assertTrue(validPalindrome.isPalidromeRecur(",."));
    Assert.assertFalse(validPalindrome.isPalidromeRecur("12   3"));
    Assert.assertFalse(validPalindrome.isPalidromeRecur("raceAcar"));
  }
}
