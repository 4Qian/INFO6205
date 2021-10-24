package edu.northeastern.qian.question4;

import java.util.LinkedList;
import java.util.Queue;

// time complexity: o(n)
// space complexity: o(1)

public class Question4 {

    public void printFullBinaryTree(TreeNode root) {
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        int totalNodeInCurrentLevel = 1;
        int printCnt = 0;
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            System.out.print(node.val);
            printCnt++;
            if (printCnt == totalNodeInCurrentLevel) {
                System.out.println();
                printCnt = 0;
                totalNodeInCurrentLevel *= 2;
            }
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }
}
