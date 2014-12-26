package ch11;

import languageTest.IntArrayPrinter;

/**
 * Created by Jun on 12/26/2014.
 *
 * PROBLEM: search an element in a sorted but shifted array
 *
 * HINT1 --obsolete--: find the offset/shift distance of the array, use conventional binary search while adjusting the index according to the shift
 *
 * HINT2: binary search; instead of using only array[mid] to determine which half to search, use head/tail too.
 */
public class SearchShiftedArray {


    public static void main(String[] args) {
        int[] test = {15,16 ,19,20,25,1,3,4,5,7,10,14};
        SearchShiftedArray finder = new SearchShiftedArray();
        System.out.println(finder.searchArray(test, 5));
    }

    int searchArray(int[] array, int target){
        if(array == null || array.length == 0) return -1;
        return searchArray(array, target, 0, array.length -1);
    }

    /**
     * find the index such that array[index] == target
     * @param array
     * @param target
     * @return index of the target, -1 if not exist
     */
    int searchArray(int[] array, int target, int head, int tail){
        if(array == null || head < 0 || tail < head || tail > array.length) return -1;

        //base case
        if(tail == head) {
            if(array[head] == target) return head;
            else return -1;
        }
        else{
            int mid = (tail - head) / 2 + head;
            if(array[mid] == target) return mid;
            if(array[head] == target) return head;
            if(array[tail] == target) return tail;
            if(array[mid] > target){
                if(array[head] > target){
                    return searchArray(array, target, mid, tail);
                }else {
                    return searchArray(array, target, head, mid);
                }
            }else {
                if(array[tail] < target){
                    return searchArray(array, target, head, mid);
                }else {
                    return searchArray(array, target, mid, tail);
                }
            }
        }
    }

}
