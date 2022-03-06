package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FibonacciTest {

  private Fibonacci fibonacci;

  @Before
  public void setUp() {
    fibonacci = new Fibonacci();
  }

  @Test
  public void fibonacciRecursive() {
    assertEquals(0, fibonacci.fibonacciRecursive(0));
    assertEquals(1, fibonacci.fibonacciRecursive(1));
    assertEquals(1, fibonacci.fibonacciRecursive(2));
    assertEquals(2, fibonacci.fibonacciRecursive(3));
    assertEquals(55, fibonacci.fibonacciRecursive(10));

  }

  @Test
  public void fibonacciDP() {
    assertEquals(0, fibonacci.fibonacciDP(0));
    assertEquals(1, fibonacci.fibonacciDP(1));
    assertEquals(1, fibonacci.fibonacciDP(2));
    assertEquals(2, fibonacci.fibonacciDP(3));
    assertEquals(55, fibonacci.fibonacciDP(10));
  }
}
