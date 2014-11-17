package ch3;

import java.util.ArrayList;

/**
 * Created by Jun on 11/14/2014.
 * 3.1 use a sigle array to implement three stacks
 */
public class SingleArrayStack<T> {
    T[] arr;
    int stackSize = 6;
    int stacksCount = 3;

    int[] indices;

//    ArrayList<MyStack> stacks;
public static void main(String[] args) {
    SingleArrayStack<Integer> stacks1 = new SingleArrayStack<Integer>();
    SingleArrayStack<Character> stacks2 = new SingleArrayStack<Character>(4);
    stacks1.push(0, 1).push(0, 2).push(1,3).push(1,4).push(2,5);
    stacks2.push(0,'t').push(0,'h').push(3,'a');
    System.out.println(stacks1);
    System.out.println(stacks2);
}

    public SingleArrayStack() {
        indices = new int[stacksCount];
        for (int i = 0; i < stacksCount; i++){
            indices[i] = i;
        }
        arr = (T[]) new Object[stackSize];
//        stacks.add(new )
    }

    /**
     *
     * @param count : the number of stacks to be created
     */
    public SingleArrayStack(int count){
        if(count < 1) throw new UnsupportedOperationException();
        this.stacksCount = count;
        indices = new int[stacksCount];
        for (int i = 0; i < stacksCount; i++){
            indices[i] = i;
        }
        arr = (T[]) new Object[stackSize];
    }

    /**
     *
     * @param i index of stack to push into
     * @param v value to push
     * @return the new SingleArrayStack object
     */
    public SingleArrayStack push(int i, T v){
        if(i > stacksCount || i < 0){
            throw new IndexOutOfBoundsException();
        }
        if(indices[i] < stackSize){
            arr[indices[i]] = v;
            indices[i] += stacksCount;
        }else {
            T[] arrLarge = (T[]) new Object[2* stackSize];
            for(int j=0; j < stackSize; j++){
                arrLarge[j] = arr[j];  //todo null pointer error?
            }
            arrLarge[stackSize] = v;
            arr = arrLarge;
            stackSize *= 2;
        }
        return this;
    }

    /**
     *
     * @param i the index of the stack to be poped (starts with 0)
     * @return the value at the top of ith stack
     */
    public T pop(int i) {
        if(i > stacksCount || i < 0){
            throw new IndexOutOfBoundsException();
        }
        if(indices[i] < stacksCount){
            return null;
        }
        indices[i] -= stacksCount;
        T v = arr[indices[i]];
//        index--;
        for(int j = stacksCount; j >=0; j --){
            if(indices[j] > stackSize/2){
                return v; // no need to shrink stack, just return.
            }
        }
        //else shirink the stack -- didn't return, thus all stacks are less than half full
        T[] arrSmall = (T[]) new Object[stackSize/2];
        stackSize /= stackSize;
        for(int k = 0; k < stackSize; k++){
            arrSmall[k] = arr[k];
        }
        arr = arrSmall;
        return v;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < stacksCount; i++){
            sb.append("stack i :");
            for(int j = i; j < indices[i]; j = j +stacksCount){
                sb.append(arr[j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Not used
     * @return
     */
//    public SingleArrayStack enlarge(){
//        for(int i = 0; i < stacksCount; i++){
//            if (indices[i] > stackSize * stacksCount ){
//                T[] arrLarge = (T[]) new Object[stacksCount * stackSize * 2];
//                for (int j = 0; j < stacksCount*stackSize; j++){
//                    arrLarge[j] = arr[j];
//                }
//                break;;
//            }
//        }
//    }

    /**
     * not used
     */
    private class MyStack{
        int step = stacksCount;
        int index;

        public MyStack(int index){
            this.index = index;
        }

        public T pop() {
            if(index == 0){
                return null;
            }
            T v = arr[index--];
//        index--;
            if(index < stackSize /2){
                T[] arrSmall = (T[]) new Object[stackSize /2];
                for (int i=0; i<index; i++){
                    arrSmall[i] = arr[i];
                }
                arr = arrSmall;
            }
            return v;
        }


    }
}
