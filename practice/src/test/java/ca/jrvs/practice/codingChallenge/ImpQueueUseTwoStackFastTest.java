package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImpQueueUseTwoStackFastTest {

  private  ImpQueueUseTwoStackFast impQueueUseTwoStackFast;

  @Before
  public void setUp(){
    impQueueUseTwoStackFast = new ImpQueueUseTwoStackFast();
  }

  @Test
  public void push(){
    Assert.assertTrue(impQueueUseTwoStackFast.empty());
    impQueueUseTwoStackFast.push(1);
    Assert.assertFalse(impQueueUseTwoStackFast.empty());
    Assert.assertEquals(1, impQueueUseTwoStackFast.peek());
    impQueueUseTwoStackFast.push(2);
    Assert.assertEquals(1, impQueueUseTwoStackFast.peek());
  }

  @Test
  public void pop() {
    impQueueUseTwoStackFast.push(1);
    impQueueUseTwoStackFast.push(2);
    impQueueUseTwoStackFast.push(3);
    Assert.assertEquals(1, impQueueUseTwoStackFast.pop());
  }

  @Test
  public void peek() {
    impQueueUseTwoStackFast.push(1);
    impQueueUseTwoStackFast.push(2);
    Assert.assertEquals(1, impQueueUseTwoStackFast.peek());
    impQueueUseTwoStackFast.push(3);
    Assert.assertEquals(1, impQueueUseTwoStackFast.peek());

  }

  @Test
  public void empty() {
    Assert.assertTrue(impQueueUseTwoStackFast.empty());
    impQueueUseTwoStackFast.push(1);
    Assert.assertFalse(impQueueUseTwoStackFast.empty());
  }
}
