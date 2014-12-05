package ch4;

import java.util.Stack;

/**
 * Created by Jun on 11/30/2014.
 *
 * PROBLEM: 4.5 check if a binary tree is BST
 *
 * HINT: flatten the tree into a stack, the stack should be monotonous.
 *
 * SOLUTION: O(n) time, O(n) space;
 *
 */
public class BSTCheck<T extends Comparable>  {

    public static void main(String[] args) {
        JunBinarySearchTree bst = new JunBinarySearchTree();
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(4);
        bst.add(5);

        TreeNode node8 = new TreeNode(8);
        TreeNode node11 = new TreeNode(11);
        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        node8.left = node6;
        node6.left = node5;
        node6.right = node11;
        node8.right = node9;
        node9.right = node10;

        BSTCheck checker = new BSTCheck();
        System.out.println(checker.isBST(bst.tn));
        System.out.println(checker.isBST(node8));

    }

    private boolean isBST(TreeNode<T> root){
        Stack<T>  treeFlattened = new Stack<T> ();
        flatenTree(root, treeFlattened); // put all nodes into a stack
        return isAssending(treeFlattened); // check if the flattened tree is a assending --  whether the original tree is BST
    }

    private void flatenTree(TreeNode<T>  root, Stack<T>  flattened){
        if(root == null || root.value == null) return;
        if(root.left != null && root.left.value != null) flatenTree(root.left, flattened);
        flattened.push(root.value);
        if(root.right != null && root.right.value != null) flatenTree(root.right, flattened);
    }

    private boolean isAssending(Stack<T>  tree){
        if(tree.isEmpty()) return true;
        T t = tree.pop();
        while (!tree.isEmpty()){
            if(t.compareTo(tree.peek()) <0 ) return false; //NOTE: assuming the tree has no duplicates.
            t = tree.pop();
        }
        return true;
    }
}
