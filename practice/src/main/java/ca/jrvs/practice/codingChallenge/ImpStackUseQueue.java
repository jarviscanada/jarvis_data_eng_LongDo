package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class ImpStackUseQueue {

  private final Queue<Integer> queue;

  public ImpStackUseQueue() {
    queue = new LinkedList<>();
  }


  /**
   * Big-O: O(n)
   */
  public void push(int val) {
    queue.add(val);
    for (int i = 0; i < queue.size() - 1; i++) {
      queue.add(queue.remove());
    }
  }

  /**
   * Big-O: O(1)
   */
  public int pop() {
    return queue.remove();
  }


  /**
   * Big-O: O(n)
   */

  public int top() {
    return queue.peek();
  }

  /**
   * Big-O: O(1)
   */
  public boolean empty() {
    return queue.isEmpty();
  }


}
