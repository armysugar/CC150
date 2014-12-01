package ch4;

import java.util.Stack;

/**
 * Created by Jun on 11/30/2014.
 *
 * PROBLEM: find the first common ancestor of two nodes in a binary tree (not BST)
 *
 * HINT: find the path from root two those two nodes, if both are not empty (both nodes in the tree), check the first position when they diverge
 *
 * SOLUTION: O(N) time O(h) space
 */
public class FirstCommonAncestor <T extends Comparable> {


    public static void main(String[] args) {
        JunBinarySearchTree jbst = new JunBinarySearchTree();
        jbst.add(11);
        jbst.add(7);
        jbst.add(3);
        jbst.add(6);
        jbst.add(10);
//        jbst.add();
//        jbst.add();
//        jbst.add();
        jbst.print();
        FirstCommonAncestor fca = new FirstCommonAncestor();
        System.out.println(fca.firstCommonAncestor(jbst.tn, new TreeNode<Integer>(3), null));
    }
    /**
     * find the first common ancestor of two nodes
     * @param root the root of binary tree
     * @param nodeA one of the node
     * @param nodeB the other node
     * @return the first common ancestor, null if nodeA or nodeB is null or not in the tree
     */
    private TreeNode<T> firstCommonAncestor(TreeNode<T> root, TreeNode<T> nodeA, TreeNode<T> nodeB){
        if(root == null || nodeA == null || nodeB == null ||
                root.value == null || nodeA.value == null || nodeB.value ==null) return null;
        Stack<TreeNode<T>> pathA = new Stack<TreeNode<T>>();
        Stack<TreeNode<T>> pathB = new Stack<TreeNode<T>>();
        findPath(root, nodeA, pathA);
        findPath(root, nodeB, pathB);
        return firstCommonAncestor(pathA, pathB);

    }

    private TreeNode<T> firstCommonAncestor(Stack<TreeNode<T>> pathA, Stack<TreeNode<T>> pathB){
        if(pathA == null || pathB == null || pathA.isEmpty() || pathB.isEmpty()) return null;
//        TreeNode<T> a;
//        TreeNode<T> b;
        TreeNode ancestor = null;
        while (!pathA.isEmpty() && !pathB.isEmpty()){
//            a = pathA.pop();
//            b = pathB.pop();
//            if(a.value.compareTo(b.value))
            if(pathA.peek().value.compareTo(pathB.peek().value) == 0) {
                ancestor = pathA.pop();
                pathB.pop();
            }else {
                break;
            }
        }
        return ancestor;
    }

    private boolean findPath(TreeNode root, TreeNode child, Stack<TreeNode<T>> path){
        if(root == null || child == null || root.value == null || child.value == null) return false;
        if(root.value.compareTo(child.value) == 0) {
            path.push(root);
            return true;
        }else if(findPath(root.left, child, path)) {
            path.push(root);
            return true;
        }else if(findPath(root.right, child, path)){
            path.push(root);
            return true;
        }else {
            return false;
        }

    }
}
