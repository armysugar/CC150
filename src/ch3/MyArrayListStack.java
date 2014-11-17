package ch3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Jun on 11/14/2014.
 */
public class MyArrayListStack<T> implements Iterable{
    ArrayList<T> stackArray;

    public MyArrayListStack(){
        stackArray = new ArrayList<T>();
    }

    public MyArrayListStack(T t){
        stackArray = new ArrayList<T>();
        stackArray.add(t);
    }

    public MyArrayListStack(Collection<T> col){
        Iterator<T> it = col.iterator();
        stackArray = new ArrayList<T>();
        while (it.hasNext()){
            stackArray.add(it.next());
        }
    }

    public T pop(){
        T top = stackArray.get(stackArray.size()-1);
        stackArray.remove(stackArray.size()-1);
        return top;
    }

    public MyArrayListStack push(T t){
        if(t == null){
//            return this; //todo should null be viewed as legitimate element?
        }
        stackArray.add(t);
        return this;
    }

//    public Iterator<T> it

    @Override
    public Iterator<T> iterator() {

        return new myArrayStackIterator(stackArray);
    }

    private class myArrayStackIterator implements Iterator{
        private ArrayList<T> arr;
        private int index=0;
//        String s = new String("test");

        public myArrayStackIterator(ArrayList<T> a){
            this.arr = a;
        }

        @Override
        public boolean hasNext() {
            return arr.size() > index;
        }

        @Override
        public Object next() {
            if(!hasNext()){
                throw new IndexOutOfBoundsException();
            }
            T e = arr.get(index);
            index++;
            return e;
        }

        @Override
        public void remove() {
            if(index == 0){
                return;
            }
            arr.remove(index-1);
        }
    }
}
