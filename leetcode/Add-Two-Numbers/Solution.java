package add.two.number;

import static org.junit.Assert.*;

import org.junit.Test;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}


public class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (null == l1)
      return l2;
    if (null == l2)
      return l1;
    ListNode result = null;
    boolean carry = false;

    while (true) {
      if (l1 != null && l2 != null) {
        int sum = l1.val + l2.val;
        if (carry)
          sum += 1;
        if (sum >= 10) {
          sum -= 10;
          carry = true;
        } else
          carry = false;
        ListNode nnode = new ListNode(sum);
        nnode.next = result;
        result = nnode;
        l1 = l1.next;
        l2 = l2.next;
      } else if (l1 != null) {
        // l2 is null;
        int sum = carry ? (l1.val + 1) : l1.val;
        if (sum >= 10) {
          sum -= 10;
          carry = true;
        } else
          carry = false;
        ListNode nnode = new ListNode(sum);
        nnode.next = result;
        result = nnode;
        l1 = l1.next;
      } else if (l2 != null) {
        int sum = carry ? (l2.val + 1) : l2.val;
        if (sum >= 10) {
          sum -= 10;
          carry = true;
        } else
          carry = false;
        ListNode nnode = new ListNode(sum);
        nnode.next = result;
        result = nnode;
        l2 = l2.next;
      } else {
        // l1 and l2 are both null;
        if (carry) {
          ListNode nnode = new ListNode(1);
          nnode.next = result;
          result = nnode;
        }
        break;
      }
    }


    return revert(result);
  }

  private ListNode revert(ListNode result) {
    ListNode re = null;
    while (result != null) {
      ListNode node = result;
      result = result.next;
      node.next = re;
      re = node;
    }
    return re;
  }

  @Test
  public void test() {
    ListNode l = new ListNode(5);
    ListNode l2 = new ListNode(5);
    ListNode l3 = this.addTwoNumbers(l, l2);
    assertEquals(l3.val, 0);
    assertEquals(l3.next.val, 1);

  }

  @Test
  public void test2() {
    ListNode l = new ListNode(8);
    ListNode ll = new ListNode(9);
    ll.next = l;
    ListNode l2 = new ListNode(1);

    ListNode l3 = this.addTwoNumbers(ll, l2);
    assertEquals(l3.val, 0);
    assertEquals(l3.next.val, 9);

  }
}
