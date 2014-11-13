package ch1;

/**
 * Created by Jun on 10/27/2014.
 * Implementanalgorithmtodetermineifastringhasalluniquecharacters.What
 * ifyoucannotuseadditionaldatastructures?
 */
public class UniChar {
    private static final String test1 = "abcdedfg";
    public static final String test2 = "abcdefa";
    public static void main(String[] args) {
        UniChar uniChar = new UniChar();
        uniChar.uniChar(uniChar.test1);
        uniChar.uniChar(uniChar.test2);
    }

    private boolean uniChar(String s) {
        for (int i = 0; i < s.length()-2; i++) {
            for (int j = i+ 1; j < s.length()-1; j++)
            if(((Character)s.charAt(i)).equals( s.charAt(j) )){
                System.out.println("duplicate at " + j + " of " + s.charAt(i));
                return false;
            }
        }
        return true;
    }
}
