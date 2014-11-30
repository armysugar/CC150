package ch4;

/**
 * Created by Jun on 11/30/2014.
 */
public class TreeNode<T extends Comparable>{
    T value;
    TreeNode left;
    TreeNode right;
    public TreeNode(){}
    public TreeNode(T v){
        this.value = v;
    }
    public TreeNode(TreeNode n){
        this.value = (T)n.value;
        this.left = n.left;
        this.right = n.right;
    }

    /**
     * add a new elemeent into the node/tree,
     * @param v new element to be added
     * @return false if v already in the tree, true otherwise
     */
    public boolean add(T v){
        //empty node, just add it here
        if(this.value == null){
            this.value = v;
            return true;
        }
        if(this.value.compareTo(v) > 0){ // small values go to the left subtree
            if(this.left == null){
                this.left = new TreeNode(v);
                return true;
            }else {
                return this.left.add(v);
            }
        }else if(this.value.compareTo(v) < 0){//large values go to the right subtree
            if(this.right == null){
                this.right = new TreeNode(v);
                return true;
            }else {
                return this.right.add(v);
            }
        }else {
            return false;  //new value equals the value at the node, return false
        }
    }

    public boolean remove(T v){
        int comparison = value.compareTo(v);
        if(comparison == 0){ //current node needs to be removed
            if(left != null){
                value = (T)left.removeLargest();
            }else if(right != null){
                value = (T)right.removeSmallest();
            }else {
                value = null;
            }
            return true;
        }else if(comparison < 0){
            if(right == null){ // input is larger, but right subtree is empty--input not in the tree, return false
                return false;
            }
            return right.remove(v);
        }else {
            if(left == null){ // input is smaller, but left subtree is empty--input not in the tree, return false
                return false;
            }
            return left.remove(v);
        }
    }

    public T removeLargest(){
        if(value == null){
            return null;
        }else if(right == null){ //right subtree empty, current node is the largest to be removed
            T r = value;
            if(left != null){  //promote left subtree if it's not empty
                value = (T)left.value;
                left = left.left;
                right = left.right;
            }else {
                value = null;
            }
            return r;
        }else {
            return (T)right.removeLargest();//largest element in right subtree, remove there
        }
    }

    public T removeSmallest(){
        if(value == null){
            return null;
        }else if(left == null){ //left subtree empty, current node is the smallest to be removed
            T r = value;
            if(right != null){  //promote left subtree if it's not empty
                value = (T)right.value;
                left = right.left;
                right = right.right;
            }else {
                value = null;
            }
            return r;
        }else {
            return (T)left.removeLargest();//largest element in right subtree, remove there
        }
    }

    public String toString(){
        if(value == null){
            return null;
        }
        StringBuffer sb = new StringBuffer();
        if(left != null){
            sb.append("(");
            sb.append(left.toString());
            sb.append(")");
        }
        sb.append(" " + value + " ");
        if(right != null){
            sb.append("(");
            sb.append(right.toString());
            sb.append(")");

        }
        return sb.toString();
    }

    public boolean equals(TreeNode n){
        if(value.compareTo(n.value) == 0
                &&
                leftEqual(this, n)
                &&
                rightEqual(this, n)){
            return true;
        }else {
            return false;
        }
    }

    private boolean leftEqual(TreeNode a, TreeNode b){
        if(a.left == null){
            if(b.left == null) return true;
            else return false;
        }else {
            return a.left.equals(b.left);
        }
    }
    private boolean rightEqual(TreeNode a, TreeNode b){
        if(a.right == null){
            if(b.right == null) return true;
            else return false;
        }else {
            return a.right.equals(b.right);
        }
    }
}