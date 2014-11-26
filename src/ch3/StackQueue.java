package ch3;

import java.util.*;

/**
 * Created by Jun on 11/26/2014.
 * Implement a queue with two stacks
 */
public class StackQueue<T>{
//    private Stack<T>
    private Stack<T> sReverse = new Stack<T>(); //used for iterator that return elements in the right order
    private Stack<T> sInverse = new Stack<T>();

    public static void main(String[] args) {
        StackQueue<String> sq = new StackQueue<String>();
        sq.add("this ");
        sq.add("is ");
        sq.add("test");
        System.out.println(sq.remove());
        System.out.println(sq);
    }

    public boolean add(T t){
        while (!sInverse.isEmpty()){
            sReverse.push(sInverse.pop());
        }
        sInverse.push(t);
        while (!sReverse.isEmpty()){
            sInverse.push(sReverse.pop());
        }
        return true;
    }

    public boolean offer(T t){
        return add(t);
    }

    public T peek(){
        return sInverse.peek();
    }

    public T element(){
        if(sInverse.isEmpty()){
            throw new EmptyStackException();
        }
        return sInverse.peek();
    }

    public T poll(){
        if(sInverse.isEmpty()){
            return null;
        }
        return sInverse.pop();
    }
    public T remove(){
        return sInverse.pop();
    }

    public String toString(){
        return sInverse.toString();
    }
}

