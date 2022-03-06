package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class ImpStackUseTwoQueue {

  private Queue<Integer> q1;
  private Queue<Integer> q2;
  private int top;


  public ImpStackUseTwoQueue() {
    q1 = new LinkedList<>();
    q2 = new LinkedList<>();

  }

  /**
   * Big O: O(1)
   */
  public void push(int val) {
    q1.add(val);
    top = val;
  }

  /**
   * Big O: O(n)
   */

  public int pop() {
    while (q1.size() > 1) {
      top = q1.remove();
      q2.add(top);
    }

    int toRemove = q1.remove();
    Queue<Integer> temp = q1;
    q1 = q2;
    q2 = temp;
    return toRemove;
  }

  public int top() {
    return top;
  }

  public boolean empty() {
    return q1.isEmpty();
  }


}
