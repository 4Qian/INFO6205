import java.util.ArrayList;

public class Test {

    public static void main (String[] args) {
        testQuestion1();
        testQuestion2();
        testQuestion3();
        testQuestion4();
        testQuestion5();
        testQuestion6();
    }

    private static void testQuestion1() {
        System.out.println("======== Question 1 =======");
        int[] arr1 = {2,0,2,1,1,0};
        int[] arr2 = {2,0,1};
        int[] arr3 = {1};
        Solutions.question1(arr1);
        printArray(arr1);
        Solutions.question1(arr2);
        printArray(arr2);
        Solutions.question1(arr3);
        printArray(arr3);
    }

    private static void testQuestion2() {
        System.out.println("======== Question 2 =======");
        ArrayList<Interval> intervals1 = new ArrayList<>();
        intervals1.add(new Interval(0, 30));
        intervals1.add(new Interval(5, 10));
        intervals1.add(new Interval(15, 20));
        intervals1.add(new Interval(7, 10));
        intervals1.add(new Interval(2, 4));
        System.out.println(Solutions.question2(intervals1));

        ArrayList<Interval> intervals2 = new ArrayList<>();
        intervals2.add(new Interval(0, 10));
        intervals2.add(new Interval(15, 20));
        intervals2.add(new Interval(30, 31));
        System.out.println(Solutions.question2(intervals2));
    }

    private static void testQuestion3() {
        System.out.println("======== Question 3 =======");
        int[] arr1 = {6,2,6,5,1,2};//9
        int[] arr2 = {1,4,3,2};//4
        System.out.println(Solutions.question3Solution1(arr1));
        System.out.println(Solutions.question3Solution1(arr2));
        System.out.println(Solutions.question3Solution2(arr1));
        System.out.println(Solutions.question3Solution2(arr2));
    }

    private static void testQuestion4() {
        System.out.println("======== Question 4 =======");
        int[] arr1 = {-4, -1, 0, 3, 10};
        int[] arr2 = {-7, -3,2, 3, 11};
        printArray(Solutions.question4Solution1(arr1));
        printArray(Solutions.question4Solution1(arr2));
        printArray(Solutions.question4Solution2(arr1));
        printArray(Solutions.question4Solution2(arr2));
    }

    private static void testQuestion5() {
        System.out.println("======== Question 5 =======");
        System.out.println(Solutions.question5Solution1("anagram", "nagaram"));
        System.out.println(Solutions.question5Solution1("cat", "rat"));
        System.out.println(Solutions.question5Solution2("anagram", "nagaram"));
        System.out.println(Solutions.question5Solution2("cat", "rat"));
    }

    private static void testQuestion6() {
        System.out.println("======== Question 6 =======");
        int[] arr1 = {2, 1, 3, 8,4};
        int[] arr2 = {2, 7, 0, 5,10};
        Solutions.question6Solution1(arr1);
        printArray(arr1);
        Solutions.question6Solution1(arr2);
        printArray(arr2);

        Solutions.question6Solution2(arr1);
        printArray(arr1);
        Solutions.question6Solution2(arr2);
        printArray(arr2);
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.print(arr[arr.length - 1]);
        System.out.println("]");
    }
}
