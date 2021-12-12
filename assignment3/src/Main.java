import java.util.Arrays;

public class Main {
    /**
     *Linked_List
     */
    public static void main(String[] args) {
        //question1
        System.out.println("====================question1=================");
        testIntersectionOfLinkedLists();//expected intersectVal = 8
        System.out.println();

        //question2
        System.out.println("====================question2=================");
        testRemoveElements();//expected is [1,2,3,4,5]
        System.out.println();

        //question3
        System.out.println("====================question3=================");
        testAddTwoNumbers();//expected is [7,0,8]
        System.out.println();

        //question4
        System.out.println("====================question4=================");
        testOddEvenList();//expected is [2,3,6,7,1,5,4]
        System.out.println();

        //question5
        System.out.println("====================question5=================");
        testMiddleNode_1();//expected is [3,4,5]
        testMiddleNode_2();//expected is [3,4,5]
        System.out.println();

        //question6
        System.out.println("====================question6=================");
        testDetectCycle();//expected is "tail connects to node index 1"
    }

    private static ListNode<Integer> connect(ListNode<Integer> cur, int nextVal) {
        cur.next = new ListNode<Integer>(nextVal);
        return cur.next;
    }

    private static ListNode<Integer> connect(ListNode<Integer> cur, ListNode<Integer> next) {
        cur.next = next;
        return cur.next;
    }

    private static void printList(ListNode<Integer> head) {
        ListNode<Integer> cur = head;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
        System.out.println("NUll");
    }


    private static void testIntersectionOfLinkedLists() {
        //[4,1,8,4,5] [5,6,1,8,4,5]
        ListNode<Integer> headA = new ListNode<>(4);
        ListNode<Integer> cur = headA;
        cur = connect(cur, 1);
        ListNode<Integer> expectedIntersection = connect(cur, 8);

        ListNode<Integer> headB = new ListNode<>(5);
        cur = headB;
        cur = connect(cur, 6);
        cur = connect(cur, 1);
        connect(cur, expectedIntersection);

        cur = connect(expectedIntersection, 4);
        connect(cur,5);

        //solution1
        ListNode<Integer> intersection_1 = Solution.getIntersectionNode_1(headA, headB);
        System.out.println("The intersection of LinkedLists is " +
                (intersection_1 == null? "NULL" : intersection_1.val));

        //solution2
        ListNode<Integer> intersection_2 = Solution.getIntersectionNode_2(headA, headB);
        System.out.println("The intersection of LinkedLists is " +
                (intersection_2 == null? "NULL" : intersection_2.val));
    }

    private static void testRemoveElements() {
        //[1,2,6,3,4,5,6]
        ListNode<Integer> head = new ListNode<>(1);
        ListNode<Integer> cur = head;
        cur = connect(cur, 2);
        cur = connect(cur, 6);
        cur = connect(cur, 3);
        cur = connect(cur, 4);
        cur = connect(cur, 5);
        connect(cur, 6);
        int val = 6;

        ListNode<Integer> node = Solution.removeElements(head, 6);
        System.out.print("The new LinkedList is " );
        printList(node);
    }

    private static void testAddTwoNumbers() {
        //l1 = [2,4,3], l2 = [5,6,4]
        ListNode<Integer> h1 = new ListNode<>(2);
        ListNode<Integer> cur1 = h1;
        cur1 = connect(cur1, 4);
        connect(cur1, 3);

        ListNode<Integer> h2 = new ListNode<>(5);
        ListNode<Integer> cur2 = h2;
        cur2 = connect(cur2, 6);
        connect(cur2, 4);

        ListNode<Integer> node = Solution.addTwoNumbers(h1,h2);
        System.out.print("The the sum as a linked list is " );
        printList(node);
    }

    private static void testOddEvenList() {
        //[2,1,3,5,6,4,7]
        ListNode<Integer> head = new ListNode<>(2);
        ListNode cur = head;
        cur = connect(cur,1);
        cur = connect(cur,3);
        cur = connect(cur,5);
        cur = connect(cur,6);
        cur = connect(cur,4);
        connect(cur,7);

        System.out.print("The reordered list is ");
        printList(Solution.oddEvenList(head));
    }

    private static void testMiddleNode_1() {
        //[1,2,3,4,5]
        ListNode<Integer> head = new ListNode<>(1);
        ListNode cur = head;
        cur = connect(cur,2);
        cur = connect(cur,3);
        cur = connect(cur,4);
        connect(cur,5);

        System.out.print("The second middle node is");
        printList(Solution.middleNode_1(head));
    }

    private static void testMiddleNode_2() {
        //[1,2,3,4,5]
        ListNode<Integer> head = new ListNode<>(1);
        ListNode cur = head;
        cur = connect(cur,2);
        cur = connect(cur,3);
        cur = connect(cur,4);
        connect(cur,5);

        System.out.print("The second middle node is");
        Solution.detectCycle(head);
    }

    private static void testDetectCycle() {
        //[3,2,0,-4]
        ListNode<Integer> head = new ListNode<>(3);
        ListNode cur = head;
        cur = connect(cur,2);
        cur = connect(cur,0);
        ListNode next = connect(cur,-4);
        next = connect(next, 2);
        next = connect(next, 0);
        connect(next, -4);

        System.out.println(
                (Solution.detectCycle(head) == null? "No cycle" : "Tail connects to node index "));
    }











}






