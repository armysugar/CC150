package ch5;

import java.util.HashMap;

/**
 * Created by Jun on 12/4/2014.
 *
 * PROBLEM: find the one missing number between 0 and n, while can only access jth bit of Array[i]
 *
 * HINT: reconstruct numbers with bits. Remove numbers of hashmap, the one remainning would be the missing one
 *
 */
public class MissingNumber {
    private static final int INT_SIZE = 32;

    public static void main(String[] args) {
        int[] arr = {0,1,2,4,5};
        MissingNumber mis = new MissingNumber();
        System.out.println(mis.findMissingNumber(arr));
    }

    private int findMissingNumber(int[] arr){
        HashMap<Integer, Boolean> map = new HashMap<>();
        for(int i = 0; i < arr.length+1; i ++) map.put(i, true);
        for(int i = 0; i < arr.length; i ++){
            int number = reconstruct(arr, i);
            map.remove(number);
        }
        Object[] found = map.keySet().toArray();
        System.out.println(found);
        return (Integer)found[0];
//        return 1;
    }

    //reconstruct ith number of array, given only bit wise access
    private int reconstruct(int[] arr, int i){
        int re = 0;
        for(int j = 0; j < INT_SIZE; j ++ ){
            re += getJthBitofI(arr, i, j) << j;
        }
        return re;
    }

    private int getJthBitofI(int[] arr, int i, int j){
        int a = arr[i];
        return getJthBit(a, j);

    }

    private int getJthBit(int a, int j){
        return (a & (1 << j)) !=0 ? 1 : 0;
    }
}
