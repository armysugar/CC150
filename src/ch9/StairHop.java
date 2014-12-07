package ch9;

/**
 * Created by Jun on 12/6/2014.
 *
 * PROBLEM: 9.1 how many possible ways to jump up a flight of stairs, if each time can hop 1, 2, 3 steps
 *
 * HINT: dynamic programming
 *
 * SOLUTION: O(n) time, O(n) space
 */
public class StairHop {
//    private static final int[] possibleSteps = {1, 2, 3};

    public static void main(String[] args) {
        StairHop hop = new StairHop();
        System.out.println(hop.possibleWays(1));
        System.out.println(hop.possibleWays(2));
        System.out.println(hop.possibleWays(3));
        System.out.println(hop.possibleWays(4));
        System.out.println(hop.possibleWays(5));
    }

    public int possibleWays(int hight){
        if(hight < 0) return -1;
        if(hight <= 1) return 1;
        if(hight == 2) return 2;
        if(hight == 3) return 4;
        int w1 = 1;
        int w2 = 2;
        int w3 = 4;
        int w = 0;
        for(int i = 4; i <= hight; i ++){
            w = w3+w2+w1;
            w1 = w2;
            w2 = w3;
            w3 = w;
        }
        return w;
    }
}
