package edu.northeastern.qian.question2;

public class Solution {

    private void mirrorTree(TreeNode node){
        if(node == null){
            return;
        }
        mirrorTree(node.left);
        mirrorTree(node.right);
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    // entry
    private boolean isFoldable(TreeNode node){
        if(node == null){
            return true;
        }
        mirrorTree(node.left);
        boolean result = areIsoMorphic(node.left, node.right);
        mirrorTree(node.left);
        return result;
    }

    public boolean areIsoMorphic(TreeNode node1, TreeNode node2){
        if(node1 == null && node2 == null){
            return true;
        }
        if(node1 == null || node2 == null){
            return false;
        }

        return  areIsoMorphic(node1.left, node2.left) &&
                areIsoMorphic(node1.right, node2.right) ;
    }


    public static class Main {
        public static void main(String[] args) {
            TreeNode root = new TreeNode(10);
            createTree(root);
            Solution solution = new Solution();
            boolean res = solution.isFoldable(root);// true
            System.out.println(res);
        }

        private static void createTree(TreeNode root){
            root.left = new TreeNode(7);
            root.right = new TreeNode(15);

            root.left.right = new TreeNode(9);
            root.right.left = new TreeNode(11);
        }
    }
}


