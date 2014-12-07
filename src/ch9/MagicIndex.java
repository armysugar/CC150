package ch9;

/**
 * Created by Jun on 12/6/2014.
 *
 * PROBLEM: 9.3 find index i such that in the sorted array array[i] == i, if exists;
 *
 * HINT: binary search
 *
 * SOLUTION: O(lgn) time, O(1) space
 */
public class MagicIndex {

    public static void main(String[] args) {
        int[] array = {-2,0,1,3,5,8};
        MagicIndex finder = new MagicIndex();
        System.out.println(finder.findMagicIndex(array));
    }

    private int findMagicIndex(int[] arr){
        if(arr == null || arr.length == 0) return -1;
        return findMagicIndex(arr, 0, arr.length -1);
    }

    private int findMagicIndex(int[] arr, int head, int tail){
        if(head > tail) return -1;
        if(head == tail) return arr[head] == head ? head : -1;
        int mid = (head + tail) / 2;
        if      (arr[mid] > mid) return findMagicIndex(arr, head, mid-1); //todo mid-1 or mid ?
        else if (arr[mid] < mid) return findMagicIndex(arr, mid+1, tail);
        else return mid; //arr[mid] == mid
    }
}
