package ch2;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Jun on 11/4/2014.
 */
public class LinkedListNode<T> {
    T data;
    LinkedListNode next;
    public LinkedListNode(){}
    public LinkedListNode(T t){
        data = t;
        next = null;
    }


    public LinkedListNode(Collection<T> list){
        LinkedListNode current = this;
        Iterator it = list.iterator();
        if(it.hasNext()){
            data = (T) it.next();
        }
        while (it.hasNext()) {
            current.next = new LinkedListNode( (T)it.next());
            current = next;
        }
    }

//    public add(T t){
//        LinkedListNode runner = next;
//        while(next != null){
//            runner = runner.next;
//        }
//    }

    /*append a new node to the tail*/
    public LinkedListNode appendToTail(LinkedListNode n){
        LinkedListNode runner = this;
        if(this.data == null){
            this.data = (T) n.data;
            this.next = n.next;
            return this;
        }
        while(runner.next != null){
            runner = runner.next;
        }
        runner.next = n;
        return this;
    }

    public LinkedListNode appendToTail(T t){
        return appendToTail(new LinkedListNode(t));
    }
    public String toString(){
        String str = "node " + data.toString();
        while (next != null){
            str = str + next.toString();
            next = next.next;
        }
        return str;
    }
}
