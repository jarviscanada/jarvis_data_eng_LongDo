package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.ListNode;

public class NthNodeFromEnd {

  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dumbNode = new ListNode();
    dumbNode.next = head;
    ListNode slow = dumbNode;
    ListNode fast = dumbNode;

    for (int i = 0; i <= n; i++) {
      fast = fast.next;
    }

    while (fast != null) {
      slow = slow.next;
      fast = fast.next;
    }

    slow.next = slow.next.next;
    return dumbNode.next;
  }
}
