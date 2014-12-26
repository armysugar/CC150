package ch9;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Jun on 12/6/2014.
 *
 * PROBLEM: 9.5 find all permutations of a string
 *
 * HINT: DP
 */
public class StringPermutation {


    private LinkedList<String> permute(String str){
        if(str == null || str.length() == 0) return null;
//        if(str.length() == 0) return
        char[] chars = str.toCharArray();
        LinkedList<char[]>  test = new LinkedList<>();
        for(int i = 0 ; i < str.length(); i ++){}
        return null;

    }
}
