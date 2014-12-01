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
        System.out.println(fca.firstCommonAncestor(jbst.tn, new TreeNode<Integer>(3),  new TreeNode(7)));
        System.out.println(fca.findFirstCommonAncestor(jbst.tn, new TreeNode(3), new TreeNode(7)));
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
        TreeNode ancestor = null;
        while (!pathA.isEmpty() && !pathB.isEmpty()){
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

    private TreeNode<T> findFirstCommonAncestor(TreeNode<T> root, TreeNode<T> a, TreeNode<T> b){
        if(root == null || a == null || b == null || root.value == null || a.value == null || b.value == null)
            return null;
        if(a.value.compareTo(b.value) == 0) return a;
        ConnectionStats rootStats = connectionStats(root, a, b);
        if(rootStats.firstAncestor) return rootStats.ancestor;
        else return null;
    }

    private ConnectionStats connectionStats(TreeNode<T> root, TreeNode<T> a, TreeNode<T> b){
        if(root == null || a == null || b == null || root.value == null || a.value == null || b.value == null)
            return new ConnectionStats(false, false, false, null);
//        else if(findfirstCommonAncestor(root.left, a, b).firstAncestor
        ConnectionStats leftStats = connectionStats(root.left, a, b);
        if(leftStats.firstAncestor) return leftStats; // return left if it got the first ancestor
        ConnectionStats rightStats = connectionStats(root.right, a, b);
        if(rightStats.firstAncestor) return rightStats; //return right if it got the first ancestor
        boolean aConnected = leftStats.connectedToFirst || rightStats.connectedToFirst || root.value.compareTo(a.value) == 0;
        boolean bConnected = leftStats.connectedToSecond || rightStats.connectedToSecond || root.value.compareTo(b.value) == 0;
        if( aConnected && bConnected){
            return  new ConnectionStats(true, true, true, root);
        }
        else return new ConnectionStats(aConnected, bConnected, false,null);
    }

    private class ConnectionStats{
        boolean connectedToFirst;
        boolean connectedToSecond;
        boolean firstAncestor;
        TreeNode<T> ancestor;

        private ConnectionStats(boolean connectedToFirst, boolean connectedToSecond, boolean firstAncestor, TreeNode<T> ancestor) {
            this.connectedToFirst = connectedToFirst;
            this.firstAncestor = firstAncestor;
            this.connectedToSecond = connectedToSecond;
            this.ancestor = ancestor;
        }
    }




}
