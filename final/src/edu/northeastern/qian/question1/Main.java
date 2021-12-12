package edu.northeastern.qian.question1;

import java.util.HashMap;
import java.util.Map;

public class Main {
    Map<Integer, Integer> indexMap;
    int[] levelOrder;
    int[] inOrder;

    public TreeNode constructTree(int[] inOrder, int[] levelOrder) {
        this.inOrder = inOrder;
        this.levelOrder = levelOrder;
        indexMap = new HashMap<>();
        for (int i = 0; i < levelOrder.length;i++) {
            indexMap.put(levelOrder[i], i);
        }
        return buildTreeHelper( 0, inOrder.length - 1);
    }

    private TreeNode buildTreeHelper( int start, int end) {
        if (start > end) {
            return null;
        }
        int index = start;
        for (int j = start + 1; j <= end; j++) {
            if (indexMap.get(inOrder[j]) < indexMap.get(inOrder[index])) {
                index = j;
            }
        }
        TreeNode root = new TreeNode(inOrder[index]);
        root.left = buildTreeHelper(start, index - 1);
        root.right = buildTreeHelper(index + 1, end);
        return root;
    }

    public static void main(String[] args) {
        int[] levelOrder = {1,2,3,4,5,6,7};
        int[] inOrder = {4,2,5,1,6,3,7};

        int[] levelOrder1 = {};
        int[] inOrder1 = {};
        Main question1 = new Main();
        TreeNode root = question1.constructTree(inOrder, levelOrder);
        System.out.println("---- print tree");
        printTreeInOrder(root); // {4,2,5,1,6,3,7}
        System.out.println("========");
        TreeNode root1 = question1.constructTree(inOrder1, levelOrder1);
        printTreeInOrder(root1);
    }

    private static void printTreeInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printTreeInOrder(root.left);
        System.out.println(root.val + " ");
        printTreeInOrder(root.right);
    }
}
