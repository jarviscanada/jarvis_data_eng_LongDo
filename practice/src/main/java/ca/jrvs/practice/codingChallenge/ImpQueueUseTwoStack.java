package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class ImpQueueUseTwoStack {

  Stack<Integer> s1;
  Stack<Integer> s2;
  private int front;

  public ImpQueueUseTwoStack() {
    s1 = new Stack<>();
    s2 = new Stack<>();
  }

  public void push(int x) {
    if (s1.empty()) {
      front = x;
    }

    while (!s1.isEmpty()) {
      s2.push(s1.pop());
    }

    s2.push(x);
    while (!s2.isEmpty()) {
      s1.push(s2.pop());
    }

  }

  public int pop() {
    return s1.pop();
  }

  public int peek() {
    return s1.peek();
  }

  public boolean empty() {
    return s1.empty();
  }

}
