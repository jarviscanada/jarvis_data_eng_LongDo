package ca.jrvs.practice.codingChallenge;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImpStackUseTwoQueueTest {

  private ImpStackUseTwoQueue impStackUseTwoQueue;

  @Before
  public void setUp() {
    impStackUseTwoQueue = new ImpStackUseTwoQueue();
  }

  @Test
  public void push() {
    Assert.assertTrue(impStackUseTwoQueue.empty());
    impStackUseTwoQueue.push(1);
    Assert.assertFalse(impStackUseTwoQueue.empty());
    Assert.assertEquals(1, impStackUseTwoQueue.top());
  }

  @Test
  public void pop() {
    impStackUseTwoQueue.push(1);
    impStackUseTwoQueue.push(2);
    impStackUseTwoQueue.push(3);
    Assert.assertEquals(3, impStackUseTwoQueue.pop());
    Assert.assertEquals(2, impStackUseTwoQueue.pop());
  }

  @Test
  public void top() {
    impStackUseTwoQueue.push(1);
    impStackUseTwoQueue.push(2);
    Assert.assertEquals(2, impStackUseTwoQueue.top());
  }

  @Test
  public void empty() {
    Assert.assertTrue(impStackUseTwoQueue.empty());
    impStackUseTwoQueue.push(1);
    Assert.assertFalse(impStackUseTwoQueue.empty());
  }

}
