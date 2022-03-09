package ca.jrvs.practice.codingChallenge;


import ca.jrvs.practice.dataStructure.ListNode;
import java.util.Objects;

public class RemoveDuplicates {

  public ListNode deleteDuplicates(ListNode head) {
    ListNode currentNode = head;
    while (currentNode != null && currentNode.next != null) {
      if (currentNode.next.val == currentNode.val) {
        currentNode.next = currentNode.next.next;
      } else {
        currentNode = currentNode.next;
      }
    }

    return head;
  }

}
