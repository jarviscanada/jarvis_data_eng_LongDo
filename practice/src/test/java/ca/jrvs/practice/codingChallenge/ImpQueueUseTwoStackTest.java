package ca.jrvs.practice.codingChallenge;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ImpQueueUseTwoStackTest {

  private ImpQueueUseTwoStack impQueueUseTwoStack;

  @Before
  public void setUp() {
    impQueueUseTwoStack = new ImpQueueUseTwoStack();
  }

  @Test
  public void push() {
    Assert.assertTrue(impQueueUseTwoStack.empty());
    impQueueUseTwoStack.push(1);
    Assert.assertFalse(impQueueUseTwoStack.empty());
    Assert.assertEquals(1, impQueueUseTwoStack.peek());
    impQueueUseTwoStack.push(2);
    Assert.assertEquals(1, impQueueUseTwoStack.peek());
  }

  @Test
  public void pop() {
    impQueueUseTwoStack.push(1);
    impQueueUseTwoStack.push(2);
    impQueueUseTwoStack.push(3);
    Assert.assertEquals(1, impQueueUseTwoStack.pop());
  }

  @Test
  public void peek() {
    impQueueUseTwoStack.push(1);
    impQueueUseTwoStack.push(2);
    Assert.assertEquals(1, impQueueUseTwoStack.peek());
    impQueueUseTwoStack.push(3);
    Assert.assertEquals(1, impQueueUseTwoStack.peek());

  }

  @Test
  public void empty() {
    Assert.assertTrue(impQueueUseTwoStack.empty());
    impQueueUseTwoStack.push(1);
    Assert.assertFalse(impQueueUseTwoStack.empty());
  }
}
