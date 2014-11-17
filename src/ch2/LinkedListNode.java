package ch2;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Jun on 11/4/2014.
 * This is a generic linked list implementeation
 */
public class LinkedListNode<T> {
    T data;

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    public void setLast(LinkedListNode last) {
        this.last = last;
    }

    public T getData() {

        return data;
    }

    public LinkedListNode getNext() {
        return next;
    }

    public LinkedListNode getLast() {
        return last;
    }

    LinkedListNode next;
    LinkedListNode last;
    public LinkedListNode(){}
    public LinkedListNode(T t){
        data = t;
        next = null;
        last = null;
    }


    public LinkedListNode(Collection<T> list){
        LinkedListNode current = this;
        Iterator it = list.iterator();
        if(it.hasNext()){
            data = (T) it.next();
        }
        while (it.hasNext()) {
            current.next = new LinkedListNode( (T)it.next());
            current.next.last = current;
            current = current.next;
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
        n.last = runner;
        return this;
    }

    public LinkedListNode appendToTail(T t){
        return appendToTail(new LinkedListNode(t));
    }
    public String toString(){
        String str = "node " + data.toString()+ " ";
        LinkedListNode runner = next;
        while (runner != null){
            str = str + " node " + runner.data.toString();
            runner = runner.next;
        }
        return str;
    }
}
