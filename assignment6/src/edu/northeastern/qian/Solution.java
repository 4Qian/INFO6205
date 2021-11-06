package edu.northeastern.qian;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    // ==================================================== Question 1 ==============================================

    /**
     * Time complexity: o(n + m), where n and m respectively represent the numbers of root1's nodes and root2's nodes
     * Space complexity: o(n + m), where n and m respectively represent the numbers of root1's nodes and root2's nodes
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // At any step, if one of these child happens to be null, we return the child of the other tree
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        TreeNode node = new TreeNode(root1.val + root2.val);
        node.left = mergeTrees(root1.left, root2.left);
        node.right = mergeTrees(root1.right, root2.right);
        return node;
    }


    // ==================================================== Question 2 ==============================================

    /**
     * Time complexity: o(n + m), where n and m are the lengths of the given trees.
     * Space complexity: o(n + m), where n and m are the lengths of the given trees.
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList();
        List<Integer> list2 = new ArrayList();

        // find all the leaves of tree "root1" and "root2"
        // store them into a list respectly
        findLeaves(root1, list1);
        findLeaves(root2, list2);

        // Compare whether the values of all leaves of tree "root1" and "root2" are the same in order
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) {
                return false;
            }
        }
        return true;
    }

    //helper method
    private void findLeaves(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            list.add(node.val);
            return;
        }
        findLeaves(node.left, list);
        findLeaves(node.right, list);
    }


    // ==================================================== Question 3 ==============================================

    /**
     * Time complexity: o(n)
     * Space complexity: o(1)
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfsPathSum(root, 0, targetSum);
    }

    private boolean dfsPathSum(TreeNode node, int pathSum, int targetSum) {
        if (node == null) {
            return false;
        }
        // when a node is a "leaf", calculate this path's sum
        if (node.left == null && node.right == null) {
            int sum = pathSum + node.val;
            return sum == targetSum;
        }
        pathSum += node.val;
        return dfsPathSum(node.left, pathSum, targetSum) || dfsPathSum(node.right, pathSum, targetSum);
    }


    // ==================================================== Question 4 ==============================================

    /**
     * Time complexity: o(n)
     * Space complexity: o(1)
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // Check if subtrees have height within 1
        return Math.abs(height(root.left) - height(root.right)) <= 1 &&
                isBalanced(root.left) && isBalanced(root.right);
    }
    // Recursively obtain the height of a tree
    private int height (TreeNode node) {
        if (node == null) {
            return -1;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }


    // ==================================================== Question 5 ==============================================

    /**
     * Time complexity: o(n)
     * Space complexity: o(1)
     *
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // corner case
        if (root == null) {
            return subRoot == null;
        }
        if (root != null && subRoot == null) {
            return true;
        }
        if (root.val == subRoot.val && isSame(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
    // check if two tree is same
    private boolean isSame(TreeNode node1, TreeNode node2) {
        if (node1 == null ) {
            return node2 == null;
        }

        if (node1 != null && node2 == null) {
            return false;
        }

        if (node1.val != node2.val) {
            return false;
        }
        return isSame(node1.left, node2.left) && isSame(node1.right, node2.right);
    }


   // ==================================================== Question 6 ==============================================

    /**
     * Time complexity: o(n)
     * Space complexity: o(1)
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
       return isFlipped(root.left,root.right);
   }
    // Check if a tree is the one after this tree is flipped
    private boolean isFlipped(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null && node2 != null) {
            return false;
        }
        if (node1 != null && node2 == null) {
            return false;
        }
        return (node1.val == node2.val) && isFlipped(node1.left, node2.right) && isFlipped(node1.right, node2.left);
    }


    // ==================================================== Question 7 ==============================================

    /**
     * Time complexity: o(n)
     * Space complexity: o(1)
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        dfs_flatten(root);
    }

    private TreeNode dfs_flatten(TreeNode node) {
        if (node == null) {
            return node;
        }

        // flatten the left sub tree and right sub tree
        TreeNode leftTail = dfs_flatten(node.left);
        TreeNode rightTail = dfs_flatten(node.right);
        TreeNode leftHead = node.left;
        TreeNode rightHead = node.right;
        // set up node, connect node to left sub linked list and right sub linked list
        node.left = null;
        if (leftHead == null) {
            leftTail = node;
        } else {
            node.right = leftHead;
        }
        if (rightHead == null) {
            return leftTail;
        }
        leftTail.right = rightHead;
        return rightTail;
    }


    // ==================================================== Question 8 ==============================================

    /**
     * Time complexity: o(n)
     * Space complexity: o(n)
     */
    Map<Integer, Map<Integer, List<Integer>>> map; // key: columnIndex, value: <rowIndex, val>
    int maxRow = Integer.MIN_VALUE;
    List<Integer> EMPTY_LIST = new ArrayList<>();

    public List<List<Integer>> verticalOrder(TreeNode root) {
        map = new HashMap<>();
        dfs(root, 0, 0);

        // convert map to result
        // find the min column index and the max column index
        int columnMin = Integer.MAX_VALUE, columnMax = Integer.MIN_VALUE;
        for (int columnIndex : map.keySet()) {
            columnMin = Math.min(columnMin, columnIndex);
            columnMax = Math.max(columnMax, columnIndex);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int col = columnMin; col <= columnMax; col++) {
            Map<Integer, List<Integer>> dataInColumn = map.get(col);
            List<Integer> listForColumn = new ArrayList<>();
            for (int row = 0; row <= maxRow; row++) {
                listForColumn.addAll(dataInColumn.getOrDefault(row, EMPTY_LIST));
            }
            res.add(listForColumn);
        }
        return res;
    }

    private void dfs(TreeNode node, int rowIndex, int columnIndex) {
        if (node == null) {
            return;
        }

        maxRow = Math.max(maxRow, rowIndex);

        // save the node's val and the rowIndex, columnIndex into the map
        map.putIfAbsent(columnIndex, new HashMap<>());
        Map<Integer, List<Integer>> dataInColumn = map.get(columnIndex);
        dataInColumn.putIfAbsent(rowIndex, new ArrayList<>());
        dataInColumn.get(rowIndex).add(node.val);

        if (node.left != null) {
            dfs(node.left, rowIndex + 1, columnIndex - 1);
        }
        if (node.right != null) {
            dfs(node.right, rowIndex + 1, columnIndex + 1);
        }
    }
}
