package edu.northeastern.qian.question3;

// time complexity: o(n)
// space complexity: o(1)
public class Question3 {
    int numOfNodesBeforeCycle = 0;
    public ListNode findMiddleNode(ListNode head) {
        ListNode cycleBegins = findCycleBegins(head);
        ListNode n = cycleBegins.next;
        int numOfNodesInCycle = 1;
        while (n != cycleBegins) {
            n = n.next;
            numOfNodesInCycle++;
        }
        int totalNumOfNodes = numOfNodesInCycle + numOfNodesBeforeCycle;
        int middleNodeIndex = (totalNumOfNodes - 1) / 2;
        ListNode middleNode = head;
        for (int i = 0; i < middleNodeIndex; i++) {
            middleNode = middleNode.next;
        }
        return middleNode;
    }

    private ListNode findCycleBegins(ListNode head) {
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
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
            numOfNodesBeforeCycle++;
        }
        return slow;
    }
}
