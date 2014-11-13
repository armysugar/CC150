package ch1;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Jun on 10/30/2014.
 */
public class StringCompression {
    private final String test1 = "";
    private final String test2 = "a";
    private final String test3 = "abbbccddddeeefffff";
    private final String test4 = "aabbccd";
    public static void main(String[] args) {
        StringCompression strCom = new StringCompression();
        System.out.println(strCom.compressString(strCom.test1));
        System.out.println(strCom.compressString(strCom.test2));
        System.out.println(strCom.compressString(strCom.test3));
        System.out.println(strCom.compressString(strCom.test4));
    }

    private String compressString(String str) {
        ArrayList<Character> chars = new ArrayList<Character>();
        Character preChar;
        int preCount = 0;
        if(str.length() <= 2){
            return str;
        }
        preChar = str.charAt(0);
        chars.add(preChar);
        preCount += 1;
        for (int i = 1; i < str.length(); i++) {
            if(preChar.equals(str.charAt(i))){
//                System.out.println("increasing preCount");
                preCount ++;
//                System.out.println(preCount);
            }else {
                chars.addAll(intChars(preCount));
//                System.out.println("put numbers" + intChars(preCount));
                chars.add(str.charAt(i));
                preChar = str.charAt(i);
                preCount = 1;
//                System.out.println("put in new char " + preChar);
            }
        }
        chars.addAll(intChars(preCount));
        if(chars.size() < str.length()){
            return arrayString(chars);
        }else {
            return str;
        }
    }

    private String arrayString(ArrayList<Character> chars) {
        String str = new String();
        for(Character c : chars){
            str = str + c;
        }
        return str;
    }

    private ArrayList<Character> intChars(int i) {
//        ArrayList<Character> chars = new ArrayList<Character>();
        Stack<Character> chars = new Stack<Character>();
        ArrayList<Character> charsOut = new ArrayList<Character>();
        int j = 0;
        char c = '0';
         do {
            j = i % 10;
            i = i/10;
            c = (char)('0' + j);
            chars.push(c);
        }while (i > 10);
        while (!chars.isEmpty()){
            charsOut.add(chars.pop());
        }
        return charsOut;
    }
}


//from the book
class MyStringBuffer{
    private ArrayList<String> arrs = new ArrayList<String>();
    private int size = 0;
//    public MyStringBuffer()
//    StringBuffer
//    CharSequence
    public void append(String s) {
        this.arrs.add(s);
        size += s.length();
    }

    public String toString( ){
//        ArrayList<Character> chars = new ArrayList<Character>();
        char[] characters = new char[size];
        int last = 0;
        for(String s : arrs){
//            chars.addAll(  new ArrayList<Character>(s.toCharArray()) );
            for(int i = 0; i < s.length(); i++){
                characters[last+i] = s.toCharArray()[i];
            }
            last += s.length();
        }
        return new String(characters);
    }
}