package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidParenthesesTest {

  private ValidParentheses validParentheses;

  @Before
  public void setUp() {
    validParentheses = new ValidParentheses();
  }

  @Test
  public void isValid() {
    Assert.assertTrue(validParentheses.isValid(""));
    Assert.assertTrue(validParentheses.isValid("()"));
    Assert.assertTrue(validParentheses.isValid("()[]{}"));
    Assert.assertTrue(validParentheses.isValid("([{}])"));

    Assert.assertFalse(validParentheses.isValid("("));
    Assert.assertFalse(validParentheses.isValid("]"));
    Assert.assertFalse(validParentheses.isValid("(])"));
    Assert.assertFalse(validParentheses.isValid("([})"));
  }
}
