package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AtoiTest {

  private Atoi atoi;

  @Before
  public void setUp() {
    atoi = new Atoi();
  }

  @Test
  public void atoiJavaBuiltin() {
    String test1 = "0";
    String test2 = "+42";
    String test3 = "0003";
    String test4 = "3000";
    String test5 = "-123";

    Assert.assertEquals(0, atoi.atoiJavaBuiltin(test1));
    Assert.assertEquals(42, atoi.atoiJavaBuiltin(test2));
    Assert.assertEquals(3, atoi.atoiJavaBuiltin(test3));
    Assert.assertEquals(3000, atoi.atoiJavaBuiltin(test4));
    Assert.assertEquals(-123, atoi.atoiJavaBuiltin(test5));

  }

  @Test
  public void myAtoi() {
    String test1 = "0";
    String test2 = "+42";
    String test3 = "0003";
    String test4 = "3000";
    String test5 = "-123";

    Assert.assertEquals(0, atoi.myAtoi(test1));
    Assert.assertEquals(42, atoi.myAtoi(test2));
    Assert.assertEquals(3, atoi.myAtoi(test3));
    Assert.assertEquals(3000, atoi.myAtoi(test4));
    Assert.assertEquals(-123, atoi.myAtoi(test5));
  }

}
