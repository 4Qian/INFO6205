package edu.northeastern.qian;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    //===========================================question 1==============================================

    /**
     * Time Complexity: o(1)
     * Space Complexity: o(1)
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        // let A denote the next node of this node
        //In order to delete this node, copy the value of  A
        node.val = node.next.val;
        //and let this node cross A and connect to the next node of A
        node.next = node.next.next;
    }


    //===========================================question 2==============================================

    /**
     * Time Complexity: o(n)
     * Space Complexity: o(1)
     *
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        //if head is null，create a new single circular list by let the next node of the new node be itself
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        //Otherwise, determine where the node should be inserted by looking for the node with the minimum value and the maximum value
        //Find the node with the max value
        Node max = head;
        while(max.next != head && max.val <= max.next.val) {
            max = max.next;
        }
        //Find the node with the min value
        Node min = max.next;
        Node cur = min;
        //If the value of the new node is between greater than max or less than min, insert new node after max
        if (insertVal >= max.val || insertVal <= min.val) {
            Node node = new Node(insertVal, min);
            max.next = node;
        } else { // Otherwise, find the node whose next is greater than intertVal
            while (cur.next.val < insertVal) {
                cur = cur.next;
            }
            Node node = new Node(insertVal, cur.next);
            cur.next = node;
        }
        return head;
    }


    //===========================================question 3==============================================

    class MyCircularDeque {
        int size;
        int k;
        DoubleListNode head;
        DoubleListNode tail;

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         * Constructor. k is the capacity, size is the current size
         *
         * @param k
         */
        public MyCircularDeque(int k) {
            head = new DoubleListNode(-1);
            tail = new DoubleListNode(-1);
            head.pre = tail;
            tail.next = head;
            this.k = k;
            this.size = 0;
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         * insert item to the front
         * @param value
         * @return If the operation is successful
         */
        public boolean insertFront(int value) {
            if (size == k)
                return false;
            DoubleListNode node = new DoubleListNode(value);
            node.next = head;
            node.pre = head.pre;
            head.pre.next = node;
            head.pre = node;
            size++;
            return true;
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         * insert item to the rear
         * @param value
         * @return If the operation is successful
         */
        public boolean insertLast(int value) {
            if (size == k)
                return false;
            DoubleListNode node = new DoubleListNode(value);
            node.next = tail.next;
            tail.next.pre = node;
            tail.next = node;
            node.pre = tail;
            size++;
            return true;
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         * delete item from the front
         * @return If the operation is successful
         */
        public boolean deleteFront() {
            if (size == 0)
                return false;
            head.pre.pre.next = head;
            head.pre = head.pre.pre;
            size--;
            return true;
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         * delete item from the rear
         * @return If the operation is successful
         */
        public boolean deleteLast() {
            if (size == 0)
                return false;
            tail.next.next.pre = tail;
            tail.next = tail.next.next;
            size--;
            return true;
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         * Get the item from the front
         * @return
         */
        public int getFront() {
            return head.pre.val;
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         * Get the item from the rear
         * @return
         */
        public int getRear() {
            return tail.next.val;
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         * Check whether the deque is empty
         * @return
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         * Check whether the deque is full
         * @return
         */
        public boolean isFull() {
            return size == k;
        }
    }

    class DoubleListNode {
        DoubleListNode pre;
        DoubleListNode next;
        int val;
        public DoubleListNode(int val) {
            this.val = val;
        }
    }


    //===========================================question 4==============================================

    /**
     * Time Complexity: o(n)
     * Space Complexity: o(1)
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prevLeft = dummy;
        ListNode leftNode = head;
        ListNode rightNode = head;

        //find the leftNode's position
        for (int i = 1; i < left; i++) {
            prevLeft = leftNode;
            leftNode = leftNode.next;
        }
        //find the rightNode's position
        for (int i = 1; i < right; i++) {
            rightNode = rightNode.next;
        }
        //Put "leftNode" after "rightNode" each time, until leftNode and rightNode are the same node
        while (leftNode != rightNode) {
            prevLeft.next = leftNode.next;
            leftNode.next = rightNode.next;
            rightNode.next = leftNode;
            leftNode = prevLeft.next;
        }
        return dummy.next;
    }


    //===========================================question 5==============================================

    /**
     * Time Complexity: o(n)
     * Space Complexity: o(1)
     *
     * @param head
     * @return
     */
    public int getDecimalValue(ListNode head) {
        int res = head.val;
        while (head.next != null) {
            //when current value is head.next.val, result will be updated as res * 2 + head.next.val
            res = res * 2 + head.next.val;
            head = head.next;
        }
        return res;
    }


    //===========================================question 6==============================================

    class FrontMiddleBackQueue {

        Deque<Integer> left;
        Deque<Integer> right;

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         */
        // Create two deques, left and right, the right side of left is connected to the left side of right
        public FrontMiddleBackQueue() {
            left = new ArrayDeque<>();
            right = new ArrayDeque<>();
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         */
        public void pushFront(int val) {
            left.addFirst(val);
            balance();
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         *
         */
        // When the number of elements is even, the number of elements in left and right is the same
        // when the number of elements is odd, right will have one more element than left
        public void pushMiddle(int val) {
            if (left.size() == right.size()) {
                right.addFirst(val);
            }else{
                left.addLast(val);
            }
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         */
        public void pushBack(int val) {
            right.addLast(val);
            balance();
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         */
        public int popFront() {
            Integer n = left.pollFirst();
            if (n == null) {
                n = right.pollFirst();
                return n == null? -1 : n;
            }else{
                balance();
                return n;
            }
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         */
        public int popMiddle() {
            if (left.size() == right.size()) {
                Integer n = left.pollLast();
                return n == null? -1 : n;
            }else{
                return right.pollFirst();
            }
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         */
        public int popBack() {
            Integer n = right.pollLast();
            balance();
            return n == null ? -1 : n;
        }

        /**
         * Time Complexity: o(1)
         * Space Complexity: o(1)
         */
        // Balance the number of elements in the two deques so that right is at most one element more than left
        private void balance() {
            if (left.size() > right.size()) {
                right.addFirst(left.pollLast());
            } else if (left.size() + 2 == right.size()) {
                left.addLast(right.pollFirst());
            }
        }
    }


    //===========================================question 7==============================================

    /**
     * Time Complexity: o(n + m)
     * Space Complexity: o(max(n, m))
     *
     * @param poly1
     * @param poly2
     * @return
     */
    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        PolyNode res = new PolyNode(0, 0);
        PolyNode cur = res;

        while (poly1 != null || poly2 != null) {
            // When any node in poly1 and poly2 is null, copy the value of the node whose corresponding position is not null to "res" linekd list
            if (poly1 == null) {
                cur.next = new PolyNode(poly2.coefficient, poly2.power);
                poly2 = poly2.next;
            }else if (poly2 == null) {
                cur.next = new PolyNode(poly1.coefficient, poly1.power);
                poly1 = poly1.next;
            }else{
                // Otherwise，add whoever has the most power first to "res" linked list
                if (poly1.power > poly2.power) {
                    cur.next = new PolyNode(poly1.coefficient, poly1.power);
                    poly1 = poly1.next;
                }else if (poly1.power < poly2.power) {
                    cur.next = new PolyNode(poly2.coefficient, poly2.power);
                    poly2 = poly2.next;
                }else{
                    // If the power of the two is the same, add the coefficient of the two nodes togetehr
                    int coef = poly1.coefficient + poly2.coefficient;
                    if (coef == 0) {
                        poly1 = poly1.next;
                        poly2 = poly2.next;
                        continue;
                    }else{
                        // If the sum of the coefficients is not 0, record this node
                        cur.next = new PolyNode(coef, poly1.power);
                        poly1 = poly1.next;
                        poly2 = poly2.next;
                    }
                }
            }
            cur = cur.next;
        }
        return res.next;
    }
}
