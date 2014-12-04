package ch4;

import ch2.LinkedListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Jun on 12/3/2014.
 *
 * solved 4.9
 *
 * PROBLEM: print out all paths in a binary tree that add up to some sum
 *
 * HINT: each node passes all of its possible paths to its ancestors as a hasmap<distance, list_of_nodes>
 *
 * SOLUTION: O((lgn)2) space, O((lgn)2) time todo check the complexity analysis
 *
 * Youaregivenabinarytreeinwhicheachnodecontainsavalue.
 * Designanalgorithmtoprintallpathswhichsumtoagivenvalue.
 * Thepathdoesnotneedto startorendattherootoraleaf.
 */
public class SumPathPrinter2 {

    public static void main(String[] args) {
        JunBinarySearchTree tree = new JunBinarySearchTree();
        tree.add(11);
        tree.add(7);
        tree.add(3);
        tree.add(6);
        tree.add(10);
        tree.print();
        SumPathPrinter2 printer = new SumPathPrinter2();
        printer.printSumPath(tree, 17);
    }

    public void printSumPath(JunBinarySearchTree tree, int sum){
        if(tree == null || tree.tn == null || tree.tn.value == null) return;
        this.printSumPath(tree.tn, sum, new HashMap<Integer, Paths>());
    }

    public void printSumPath(TreeNode root, int sum, HashMap<Integer, Paths> parentPaths){
        if(root == null || root.value == null || parentPaths == null) return;


        //append this node to existing paths
        if(!parentPaths.isEmpty()){
            for (Paths path : parentPaths.values()){
                path.append(root);
            }
        }
        //modify the path length  after appending the new node
        HashMap<Integer, Paths> mid = new HashMap<Integer, Paths>();
        for(Integer index : parentPaths.keySet()){
            mid.put(index + (Integer) root.value, parentPaths.get(index));
        }
        parentPaths = mid;
        //add the value of this root into the hashmap
        Paths existing = parentPaths.get(root.value);
        if(existing != null){
            existing.addPath(root);
        }else {
            parentPaths.put((Integer) root.value,
                    new Paths(root));
        }

        //find existing paths that add up to sum
        Paths bingo = parentPaths.get(sum);
        if(bingo != null){
            System.out.println(bingo);
        }

        //pass on existing paths to children
        HashMap legacyLeft = copyHashmap(parentPaths);
        HashMap legacyRight = copyHashmap(parentPaths);
        printSumPath(root.left, sum, legacyLeft);
        printSumPath(root.right, sum, legacyRight);
    }

    private HashMap copyHashmap(HashMap origin){
        HashMap copy = new HashMap();
        for(Object key : origin.keySet()){
            copy.put(key, ((Paths)origin.get(key)).deepCopy());
        }
        return copy;
    }

    private class Paths{
        int pathLength;
        ArrayList<LinkedList<TreeNode>> paths;

        public Paths(){paths = new ArrayList<LinkedList<TreeNode>>();}
        public Paths(int length, ArrayList<LinkedList<TreeNode>> paths) {pathLength = length; this.paths = paths;}
        public Paths(TreeNode node) {
            pathLength = (Integer)node.value;
            LinkedList list = new LinkedList();
            list.add(node);
            paths = new ArrayList<LinkedList<TreeNode>>();
            paths.add(list);
        }
        public void append(TreeNode node){
            if(paths.isEmpty() || node == null || node.value == null) return;
            for(LinkedList list : paths){
                list.add(node);
            }
            pathLength += (Integer) node.value;
        }

        public void addPath(LinkedList<TreeNode> newPath) {paths.add(newPath);} //todo should haved checked the path's length

        public void addPath(TreeNode singleNode) {
            LinkedList path = new LinkedList();
            path.add(singleNode);
            this.addPath(path);
        }

//        public void addPath()

        public Paths deepCopy(){
            ArrayList pathsCopy = new ArrayList();
            for(LinkedList list : paths){
                LinkedList copyList = new LinkedList();
                copyList.addAll(list);
                pathsCopy.add(copyList);
            }
            Paths clone = new Paths();

            clone.pathLength = pathLength;
            clone.paths = pathsCopy;
            return clone;
        }

        public String toString(){
            if(paths.isEmpty()) return null;
            StringBuffer sb = new StringBuffer();
            for (LinkedList path : paths){
                sb.append("path of length " + pathLength + " : " + path);
            }
            return sb.toString();
        }
    }

}
