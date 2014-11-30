package ch4;

import java.util.Stack;

/**
 * Created by Jun on 11/30/2014.
 *
 * PROBLEM: 4.1 Check if a bynary tree is balanced
 *
 * HINT: to determine the height of a node, it's children's height must be determined first -- post oder traversal
 *
 * SOLUTION: solved in O(n) time and O(lgn) space.
 *
 * Implementafunctiontocheckifabinarytreeisbalanced.Forthepurposesof
 * thisquestion,abalancedtreeisdefinedtobeatreesuchthattheheightsofthe
 * twosubtreesofanynodeneverdifferbymorethanone.
 */
public class TreeBalanceCheck {
    private final int THRESHOLD; //the threshold to determine the "balance" of a tree

    public TreeBalanceCheck(int v){
        THRESHOLD = v;
    }

    public static void main(String[] args) {
        JunBinarySearchTree<Integer> jbst = new JunBinarySearchTree<Integer>();
        TreeBalanceCheck balanceCheck = new TreeBalanceCheck( 2);
        jbst.add(1);
        jbst.add(2);
        jbst.add(3);
        jbst.add(4);
        System.out.println(jbst);
        System.out.println(balanceCheck.isBalanced(jbst));

    }

    /**
     *
     * @param jbst BinaryTree to be checked
     * @return true if |left.hight - right.hight| < threshold
     */
    private boolean isBalanced(JunBinarySearchTree jbst){
        if(jbst == null || jbst.tn == null) return true;
        return isBalanced(jbst.tn, THRESHOLD);
    }

    private boolean isBalanced(TreeNode tn, int threshold){
       return storeHights(tn, threshold).balance;
    }


    /**
     * store hight and balance info in a MyBalance obj
     * @param tn node to be checked
     * @param threshold threshold to determine balance, hight_diff < threshold considered balanced
     * @return MyBalance obj: obj.balance -- whether this tree is balanced, obj.hight -- the hight of root
     */
    private MyBalance storeHights(TreeNode tn, int threshold){
        if(tn == null){
            return new MyBalance(true, -1);
        }else if (tn.right == null && tn.left == null){
            return new MyBalance(true, 0);
        }else if(tn.right == null){
            MyBalance leftBalance = storeHights(tn.left, threshold);
            if(leftBalance.balance == true && leftBalance.hight < threshold){ //if left subtree is balanced and its hight is less than threshold
                return new MyBalance(true, leftBalance.hight + 1);
            }else return new MyBalance(false, leftBalance.hight +1);
        }else if(tn.left == null){
            MyBalance rightBalance = storeHights(tn.right, threshold);
            if(rightBalance.balance == true && rightBalance.hight < threshold){
                return new MyBalance(true, rightBalance.hight + 1);
            }else return new MyBalance(false, rightBalance.hight +1);
        }else {//both left and right subtrees are not empty
            MyBalance leftBalance = storeHights(tn.left, threshold);
            MyBalance rightBalance = storeHights(tn.right, threshold);
            return balanceMatch(leftBalance, rightBalance, threshold);
        }

    }

    /**
     * using the balance info of two subtrees to determine the balance of tree at the root
     * @param left
     * @param right
     * @param threshold
     * @return
     */
    private MyBalance balanceMatch(MyBalance left, MyBalance right, int threshold){
        if(left == null & right == null) return new MyBalance(true, 1);
        else if(left == null) return new MyBalance(right.hight < threshold, right.hight + 1);
        else if(right == null) return new MyBalance(left.hight < threshold, left.hight + 1);

        else return new MyBalance(
                    left.hight < right.hight + threshold && right.hight < left.hight + threshold,
                    left.hight > right.hight ? left.hight + 1: right.hight + 1
            );
    }


    private class MyBalance{
        boolean balance;
        int hight;
        public MyBalance(boolean balance, int hight) {
            this.balance = balance;
            this.hight = hight;
        }
        public MyBalance(){}
    }


}
