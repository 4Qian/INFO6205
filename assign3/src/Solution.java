import java.util.HashSet;
import java.util.Set;

public class Solution {
    //==========================================Question1===========================================
    /**
     * question1 - solution1:
     *
     * Time complexity : o(n + m)
     * Space complexity : o(m) or o(n)
     *
     * @param headA
     * @param headB
     * @return
     */
    public  ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        Set<ListNode> nodesOfA = new HashSet<>();
        //add all nodes of list A into a HashSet
        while (headA != null) {
            nodesOfA.add(headA);
            headA = headA.next;
        }
        // starting from headB, find the first node that exists in the HashSet
        while (headB != null) {
            if (nodesOfA.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /**
     * question1 - solution2: A brute-force solution
     *
     * Time complexity : o(n * m)
     * Space complexity : O(1)
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        ListNode originalHeadB = headB;
        while (headA != null) {
            //check if list B has the node in ListA
            headB = originalHeadB;
            while (headB != null) {
                if (headA == headB) {
                    return headA;
                }
                headB = headB.next;
            }
            headA = headA.next;
        }
        return null;
    }

    //==========================================Question2===========================================
    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public  ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null) {
            // delete cur by making prev linking to cur's next node
            if (cur.val == val) {
                prev.next = cur.next;
            }else {
                prev = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    //==========================================Question3===========================================

    /**
     * Time complexity : O(max(m, n))
     * Space complexity : O(max(m, n))
     *
     * @param l1
     * @param l2
     * @return
     */
    public  ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(0);//helper ListNode
        ListNode cur = dummy;

        while (l1 != null || l2 != null) {
            // get l1's value
            int n = l1 == null? 0 : l1.val;
            // get l2's value
            int m = l2 == null? 0 : l2.val;
            int sum = carry + n + m;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // consider the case that there is an extra carry of 1
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }

    //==========================================Question4===========================================

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     * @param head
     * @return
     */
    public  ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode eHead = even;// the head of evenList

        // iterate through the list by moving 1 step for odd pointer and even pointer each time
        while (even != null && even.next != null) {
            odd.next = even.next; // even's next should be the next odd node
            odd = odd.next; // move odd node forward
            even.next = odd.next; // odd's next should be the next even node
            even = even.next; // move even node forward
        }
        // connect oddList and evenList
        odd.next = eHead;
        return head;
    }

    //==========================================Question5===========================================

    /**
     * Question5 - solution1:
     *
     * Time complexity : O(n)
     * Space complexity : O(1)
     *
     * @param head
     * @return
     */
    public  ListNode middleNode_1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // Make fast pointer twice as fast as the slow one, util fast reaches the end of the list.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // When fast pointer reaches the end of list, slow pointer is what we want
        return slow;
    }

    /**
     * Question5 - solution2:
     *
     * Time complexity : O(n)
     * Space complexity : O(n)
     *
     * @param head
     * @return
     */
    public  ListNode middleNode_2(ListNode head) {
        ListNode[] array = new ListNode[100];
        int index = 0;
        // Add all nodes into an array, we can retrieve the middle node by index
        while(head != null) {
            array[index++] = head;
            head = head.next;
        }
        return array[index / 2];
    }

    //==========================================Question6===========================================

    /**
     * Time complexity : O(n)
     * Space complexity : O(1)
     * @param head
     * @return
     */
    public  ListNode detectCycle(ListNode head) {
        // slow pointer and fast pointer both start from head
        ListNode slow = head;
        ListNode fast = head;

        // stop either:
        //  1. the fast pointer loop around a cycle and meet the slow
        //  2. fast pointer reaches end
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        // If fast node can reach the "null", this means there is no cycle.
        if (fast == null || fast.next == null) {
            return null;
        }

        // A is the starting point, B is where circle begins(what we want for the return value)

        // if cycle exists, fast pointer will meet slower pointer at somewhere at the circle. Let C denote this location.
        // let f denote the distance of fast pointer moves
        // let s denote the distance of slow pointer moves
        // let P denote the perimeter of the circle

        // then we have f = 2x and s = x
        // Since fast pointer dose meet slow pointer, we have f - s = k * P (where k >= 1)
        // And since f - s = 2x - x = x = s, we have s = k * P (where k >= 1)
        // Since s = AB + BC + n * P (where n >=0), we have AB = (k-n) * P - BC = P - BC + m * P (where m >= 0)

        // With "AB = P - BC + m * P (where m >= 0)", fast point can travel a distance of AB reaching B, while slow point
        // can go a distance of "P - BC" (which brings it to B from C) plus m complete cycles (so it stays at B).
        // So both pointers will meet at "B"
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
