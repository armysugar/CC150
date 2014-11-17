package ch3;

import ch2.LinkedListNode;

/**
 * Created by Jun on 11/14/2014.
 */
public class MyLinkedQueue<T> {
    LinkedListNode<T> head;
    LinkedListNode<T> tail;

    public MyLinkedQueue ( ){
        
    }

    public MyLinkedQueue(T t){
        this.head = new LinkedListNode<T>(t);
        this.tail = head;
    }

    public MyLinkedQueue enqueue(T t){
        LinkedListNode newTail = new LinkedListNode(t);
        tail.appendToTail(newTail);
        tail = newTail;
        return this;
    }

    public T dequeque(){
        T v = head.getData();
        head.getNext().setLast(null);
        head = head.getNext();
        return v;
    }



}
