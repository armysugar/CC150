package ch1;

import java.util.Hashtable;

/**
 * Created by Jun on 10/27/2014.
 */
public class IsPermutation {
    private static Hashtable<Character, Integer> count = new Hashtable<Character, Integer>();
    public static void main(String[] args) {
        String a1 = "abcdefg";
        String b1 = "gfedcba";
        String a2 = "abc";
        String b2 = "ab";
        String a3 = "abced";
        String b3 = "abcee";
        isPermutation(a1, b1);
//        isPermutation(a2, b2);
//        isPermutation(a3, b3);

    }

    private static boolean isPermutation(String a, String b) {
        count.clear();
        if(null == a || null == b){
            System.out.println("false because of null");
            return false;
        }
        if(a.length() != b.length()){
            System.out.println("false because of length");
            return false;
        }
        //add one string in to hash table,
        for (Character ch : a.toCharArray()) {
            System.out.println(ch+ " ");
            if(count.contains(ch)){
                count.put(ch,count.get(ch)+1);
                System.out.println("increase " + ch);
            }else {
                count.put(ch, 1);
                System.out.println("put " + ch);
            }
        }

        System.out.println(count);
        for(Character ch : b.toCharArray()){
            if(!count.contains(ch)){
                System.out.println("false doesn't contain " + ch);
                return false;
            }else {
                if(count.get(ch) == 0){
                    System.out.println("error at " + ch);
                    return false;
                }
                count.put(ch,count.get(ch)-1);
            }
        }
        System.out.println( a+" is permutations of " + b);
        return true;
    }
}
