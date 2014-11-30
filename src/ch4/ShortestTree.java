package ch4;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Jun on 11/30/2014.
 *
 * PROBLEM: create shortest tree, given sorted array of integer
 *
 * HINT: medium of a (sub) tree should be the root
 *
 * SOLUTION: solved in O(n) time
 *
 * 4.3 Givenasorted(increasingorder)arraywithuniqueintegerelements,writean
 * algorithmtocreateabinarysearchtreewithminimalheigh
 */
public class ShortestTree {

    public static void main(String[] args) {
        int[] test = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        ShortestTree st = new ShortestTree();
        JunBinarySearchTree result = st.createShortestTree(test, new JunBinarySearchTree());
        result.print();
        JunBinarySearchTree resultSmart = st.shortestTreeSmart(test);
        resultSmart.print();
    }

    /**
     * build a shortes tree with sorted array. Runs in O(nlgn) time
     * Tree built top-bottom
     * @param arr
     * @param shortestTree
     * @return
     */
    private JunBinarySearchTree createShortestTree(int [] arr, JunBinarySearchTree shortestTree){
        if(arr == null || arr.length == 0) return null;
        if(arr.length < 3) {
            for(int v : arr) shortestTree.add(v);
            return shortestTree;
        }
        int mid = (arr.length-1)/2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid+1, arr.length);
//        System.out.println(left.length);
//        System.out.println(right.length);
        shortestTree.add(arr[mid]);
        createShortestTree(left, shortestTree);
        createShortestTree(right, shortestTree);
        return shortestTree;
    }

    /**
     * Because of the property of sorted array, we could determine the location of each number in the tree before building it
     * Exploiting this, we could build the tree in O(n) time in a bottom-top manner
     */

    private JunBinarySearchTree shortestTreeSmart(int[] arr){
        return new JunBinarySearchTree(shortestTreeNode(arr));
    }

    /**
     * build the shortest BST with sorted array in O(n) time and O(n) space
     * @param arr
     * @return
     */
    private TreeNode shortestTreeNode(int[] arr){
        if(arr == null || arr.length == 0) return null;
        if(arr.length == 1) return new TreeNode(arr[0]);
        if(arr.length == 2) {
            System.out.println("new node " + arr[0]);
            TreeNode root = new TreeNode(arr[0]);
            System.out.println("new node " + arr[1]);
            root.left = new TreeNode(arr[1]);
        }
        int mid = (arr.length-1) / 2;
        System.out.println("length: " + arr.length + " mid " + mid);
        TreeNode root = new TreeNode(arr[mid]);
        TreeNode left = shortestTreeNode(Arrays.copyOfRange(arr, 0, mid));
        TreeNode right = shortestTreeNode(Arrays.copyOfRange(arr, mid + 1, arr.length));
        root.left = left;
        root.right = right;
        return root;
    }
}
