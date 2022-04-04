package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.ListNode;

public class MiddleOfLinkedList {

  public ListNode middleNode(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

    }

    return slow;
  }

}
