package ch3;

import ch2.LinkedListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Jun on 11/14/2014.
 * stackoncethepreviousoneexceedscapacity.SetOfStacks.push()and
 * SetOfStacks.pop()shouldbehaveidenticallytoasinglestack(thatis,popO
 * shouldreturnthesamevaluesasitwouldiftherewerejustasinglestack)
 */
public class SetOfStacks<T> {
    private final int capacity;
//    LinkedListNode stacks;
//    private int tailStack = 0;
    ArrayList<Stack<T>> stacks = new ArrayList<Stack<T>>();
    ArrayList<Integer> indices = new ArrayList<Integer>();



    public SetOfStacks(int capacity) {
        this.capacity = capacity;
        stacks.add(new Stack<T>());
        indices.add(0);
    }

    public SetOfStacks(){
        this(9);//a haphazard initial capacity
    }

    public static void main(String[] args) {
        SetOfStacks<Integer> ss1 = new SetOfStacks<Integer>();
        SetOfStacks<String> ss2 = new SetOfStacks<String>(1);
        ss1.push(1).push(2).push(3).push(4).push(1).push(2).push(3).push(4).push(1).push(2).push(3).push(4);
        ss2.push("this").push("is").push("a").push("test");
        System.out.println(ss1);
        System.out.println(ss2);

    }

    public SetOfStacks push(T v){
        //check if the last stack is full
        if(stacks.get(stacks.size()-1).size() == capacity){
            stacks.add(new Stack<T>());
        }
        stacks.get(stacks.size()-1).push(v);
        indices.set(indices.size()-1, 1+indices.get(indices.size()-1));
        return this;
    }

    public T pop(){
        T v = stacks.get(stacks.size()-1).pop();
        if(stacks.get(stacks.size()-1).isEmpty()){
            stacks.remove(stacks.size()-1);
            indices.remove(indices.size()-1);
        }
        return v;
    }

    public T popAt(int index){
        if(index < 0 || index > stacks.size()){
            throw new IndexOutOfBoundsException();
        }
        T v = stacks.get(index).pop();
        return v; //todo : should empty stacks be removed? Pro: save memory, Con: can potAt(0) "forever"
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(Stack s : stacks){
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }


    /**
     * not used
     * @param <T>
     */
    private class IndexedStack<T>{
        int index = 0;
        int capacity;
        Stack<T> stack = new Stack<T>();
        public IndexedStack(int capacity){
            this.capacity = capacity;
        }
        public IndexedStack push(T v){
            if(index < 0){
                return null;
            }
            return this;
        }
    }

}
