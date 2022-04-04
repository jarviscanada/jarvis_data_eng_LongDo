package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.ListNode;

public class LinkedListCycle {

  public boolean hasCycle(ListNode head) {
    if (head == null) {
      return false;
    }
    ListNode fast = head.next;
    ListNode slow = head;
    while (slow != null && fast != null && fast.next != null) {
      if (slow == fast) {
        return true;
      }
      slow = slow.next;
      fast = fast.next.next;
    }

    return false;
  }
}
