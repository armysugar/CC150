package ch4;

/**
 * Created by Jun on 11/28/2014.
 */
public class JunBinarySearchTree<T extends Comparable> {
    TreeNode tn;

    public JunBinarySearchTree(){}
    public JunBinarySearchTree(T t){
        tn = new TreeNode(t);
    }
    public JunBinarySearchTree(TreeNode tn) {this.tn = tn;}

    public void add(T t){
        tn = add(tn, t);
    }

    private TreeNode add(TreeNode node, T t){
        if(node == null){
            return new TreeNode(t);
        }else if(node.value.compareTo(t) < 0){ //large values add to the right subtree
            if(node.right == null){
                node.right = new TreeNode(t);
            }else {
                add(node.right, t);
            }
        }else {                               //small values add to the left subtree
            if(node.left == null){
                node.left = new TreeNode(t);
            }else {
                add(node.left, t);
            }
        }
        return node;
    }

    private boolean contains(T t){
        if(tn == null && t != null){
            return false;
        }else {
            return contains(tn, t);
        }

    }

    private boolean contains(TreeNode node, T t){
        if(node == null && t != null){
            return false;
        }
        if(node.value.compareTo(t) == 0)return true;
        else if(node.value.compareTo(t) < 0){
            if(node.left == null) return false;
            else return contains(node.left, t);
        }
        else{
            if(node.right == null) return false;
            else return contains(node.right, t);
        }
    }

    public String toString(){
        if(tn == null || tn.value == null) return null;
        StringBuffer sb = new StringBuffer();
        sb.append(tn.left);
        sb.append(") " + tn.value + " (");
        sb.append(tn.right);
        return sb.toString();
    }

    public void print(){
        tn.print();
    }
}
