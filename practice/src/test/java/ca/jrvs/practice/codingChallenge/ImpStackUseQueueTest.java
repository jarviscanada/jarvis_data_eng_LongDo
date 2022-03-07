package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImpStackUseQueueTest {

  private ImpStackUseQueue impStackUseQueue;

  @Before
  public void setUp() {
    impStackUseQueue = new ImpStackUseQueue();
  }

  @Test
  public void push() {
    Assert.assertTrue(impStackUseQueue.empty());
    impStackUseQueue.push(1);
    Assert.assertFalse(impStackUseQueue.empty());
    Assert.assertEquals(1, impStackUseQueue.top());
  }

  @Test
  public void pop() {
    impStackUseQueue.push(1);
    impStackUseQueue.push(2);
    impStackUseQueue.push(3);
    Assert.assertEquals(3, impStackUseQueue.pop());
    Assert.assertEquals(2, impStackUseQueue.pop());
  }

  @Test
  public void top() {
    impStackUseQueue.push(1);
    impStackUseQueue.push(2);
    Assert.assertEquals(2, impStackUseQueue.top());
  }

  @Test
  public void empty() {
    Assert.assertTrue((impStackUseQueue.empty()));
  }

}
