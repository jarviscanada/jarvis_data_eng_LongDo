package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OddEvenTest {

  private OddEven oddEven;

  @Before
  public void setUp() {
    oddEven = new OddEven();
  }

  @Test
  public void oddEven() {
    Assert.assertEquals("even", oddEven.OddEvenFunc(0));
    Assert.assertEquals("even", oddEven.OddEvenFunc(2004006));
    Assert.assertEquals("even", oddEven.OddEvenFunc(888));
    Assert.assertEquals("odd", oddEven.OddEvenFunc(1));
    Assert.assertEquals("odd", oddEven.OddEvenFunc(15));

  }
}
