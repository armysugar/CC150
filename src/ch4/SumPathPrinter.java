package ch4;

import ch2.LinkedListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Jun on 12/1/2014.
 *
 * NOTE: some bugs remain in this solution:
 *
 * PROBLEM: print all paths that sum up to a given amount
 *
 * HINT: dynamic programming, every node path its hashmap<sum, path> to its children;
 *      if some node can form the sum with its ancestors, print out that path.
 *
 * SOLUTION:
 *
 * Youaregivenabinarytreeinwhicheachnodecontainsavalue.
 * Designanalgorithmtoprintallpathswhichsumtoagivenvalue.
 * Thepathdoesnotneedto startorendattherootoraleaf.
 */
public class SumPathPrinter<T extends Comparable> {

    public static void main(String[] args) {
        JunBinarySearchTree jbst = new JunBinarySearchTree();
        jbst.add(11);
        jbst.add(7);
        jbst.add(3);
        jbst.add(6);
        jbst.add(10);
        jbst.print();
        SumPathPrinter printer = new SumPathPrinter();
        printer.printSumPaths(jbst, 17);
    }

    private void printSumPaths(JunBinarySearchTree tree, int sum){
        TreeNode tn = tree.tn;
        if(tn.value.compareTo(sum) == 0) System.out.println(tn.value);
        printSumPaths(tree.tn, sum, new HashMap<Integer, Distance>());

    }

    private void printSumPaths(TreeNode node, int sum, HashMap<Integer, Distance> paths){
        if(node == null || node.value == null) return;
        Distance bingo = paths.get(sum - (Integer)node.value);
        if(bingo != null){
            bingo.append(node);
            bingo.print();
        }

        Set distances = paths.keySet();
        HashMap<Integer, Distance> newPaths = new HashMap<Integer, Distance>();
        for(Object dis : distances){
            newPaths.put((Integer)dis + (Integer)node.value, paths.get(dis).append(node));
        }
        LinkedListNode newNode = new LinkedListNode(node);
        ArrayList<LinkedListNode> arr = new ArrayList<LinkedListNode>();
        arr.add(newNode);
        newPaths.put((Integer)node.value, new Distance((Integer)node.value, arr));
        HashMap extraPahts = (HashMap)newPaths.clone(); //todo , change shollow copy to deep copy (very deep)
        printSumPaths(node.left, sum, newPaths);
        printSumPaths(node.right, sum, extraPahts);
    }


    private class Distance {
        int pathSum;
        //        LinkedListNode<LinkedListNode<T>> paths;
        ArrayList<LinkedListNode> paths;

        public Distance(){}
        public Distance(int sum) {pathSum = sum; paths = new ArrayList<LinkedListNode>();}
        public Distance(int sum, ArrayList<LinkedListNode> paths){ this.pathSum = sum; this.paths = paths;}
        public Distance addPath(LinkedListNode newPath){paths.add(newPath); return this;}
        public Distance addPath(TreeNode node) {
            addPath(new LinkedListNode(node));
            return this;
        }

        public Distance append(T t){
            for(LinkedListNode lln : paths){
                lln.addToHead(t);
            }
            pathSum += (Integer)t;
            return this;
        }

        public Distance append(TreeNode node){
            for(LinkedListNode lln : paths){
                lln.addToHead(node);
            }
            pathSum += (Integer) node.value;
            return this;
        }

        public void print( ){
//            System.out.println("print in Distance" + pathSum + paths);
            if(paths.isEmpty()) return;
            for(LinkedListNode path : paths){
                System.out.println(path);  //got in here, but didn't print anything ...
//                for(TreeNode node : (LinkedListNode)path){
//                    System.out.println();
//                }
            }
        }
    }


}
