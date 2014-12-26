package ch11;

/**
 * Created by Jun on 12/26/2014.
 *
 * PROBLEM: search in a array of string which is interspersed with empty strings
 *
 * HINT: when choosing mid, may not use the one with the mid-index, but use the nearest one with a effective value
 */
public class InterspersedArraySearch {

    public static void main(String[] args) {
        String[] test = {"at","" ,"" ,"" ,"ball","" ,"" ,"car"};
        InterspersedArraySearch finder = new InterspersedArraySearch();
        System.out.println(finder.findString(test, "ball"));
        System.out.println(finder.findString(test, "car"));
    }

     int findString(String[] array, String s){
         if(array == null || array.length == 0 || s == null || s.length() == 0) return -1;
         //find head to start with
         int head = 0;
         int length = array.length - 1;
         while (head <= length){
             if(array[head].length() == 0) head ++;
             else break;
         }
         if(head > length) return -1; // array has only ""

         //find tail to end with
         int tail = length;
         while (tail >= 0){
             if(array[tail].length() == 0) tail--;
             else break;
         }

        return findString(array, s, head, tail);
     }

    /**
     * search string[] array, starting from (inclusive) head to head (inclusive) to find the index such that array[index] equals string s
     * @param array
     * @param s
     * @param head
     * @param tail
     * @return
     */
    int findString(String[] array, String s, int head, int tail){
        if(array == null || s == null || head < 0 || tail < head || tail >= array.length || s.length() == 0) return -1;

        //find effective head and tail
        for(int i = head; i <= tail; i ++){
            if(array[i].length() == 0) head++;
            else break;
        }
        if(head > tail) return -1; //"empty" array
        for(int i = tail; i >= head; i--){
            if(array[i].length() == 0) tail --;
            else break;
        }


        //base case
        if(head == tail){
            if(array[head].compareTo(s) == 0) return head;
            else return -1;
        }

        //find effective mid
        int mid = head + (tail - head) / 2;
        //if array[mid] is "", we need to find a effective mid
        int effecitveMid = effectiveMid(array, head, tail, mid);
//        if(array[mid].length() == 0){
//            while (lowerMid > head){
//                if(array[lowerMid].length() == 0) lowerMid--;
//                else break;
//            }
//        }
//        int lower = mid;

        int flag = array[effecitveMid].compareTo(s);
        if( flag == 0) return effecitveMid;
        //recursion
        else if(flag > 0) return findString(array, s, head, mid -1);
        else return  findString(array, s, mid+1, tail);
    }

    int effectiveMid(String[] array, int head, int tail, int indexMid){
        for(int i = indexMid; i >= head; i --){
            if(array[i].length() != 0) return i;
        }
        for(int i = indexMid; i <= tail; i ++){
            if(array[i].length() != 0) return i;
        }
        return -1;
    }
}
