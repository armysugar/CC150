package ch5;

/**
 * Created by Jun on 12/4/2014.
 *
 * PROBLEM: print the next largest/smallest
 * Givenapositiveinteger,printthenextsmallestandthenextlargestnumber
 * thathavethesamenumberof1bitsintheirbinaryrepresentation
 */
public class NextInt {
    private static final int INT_SIZE  = 32;


    public static void main(String[] args) {
        int a = 0b0010110;
        System.out.println(" a " + Integer.toBinaryString(a));
        NextInt next = new NextInt();
        try {
            System.out.println(Integer.toBinaryString(next.nextLarger(a)));
            System.out.println(Integer.toBinaryString(next.nextSmaller(a)));
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
    /**
     * find the next smallest int that has the same number of 1's in binary form as the input
     * @param a
     * @return
     */
    private int nextSmaller(int a) throws Exception{
        int former = 0;
        int latter = 0;
        int runner = 1;
        for(int i = 0; i < INT_SIZE-1; i ++){
            if(!bitSet(a, i) && bitSet(a, i + 1)){
                System.out.println("found change at " + i + " for " + Integer.toBinaryString(a));
                return convertSmaller(a, i);
            }
        }
        throw new Exception("invalid input");
    }

    /**
     * find the the first location in the input where bit value changes from 1 to 0;
     * move this 1 to the next 0 should give us the next smallest number with the same 1's
     */
    private int nextLarger(int a) throws Exception{
        int runner = 1;
        int former = a & runner;
        int latter = 0;
        for(int i = 0; i < INT_SIZE-1; i ++){
            if(bitSet(a, i) && !bitSet(a, i + 1)){
                System.out.println("found change at " + i + " for " + Integer.toBinaryString(a));
                return convertLarger(a, i);
            }
        }
        throw new Exception("invalid input");
    }

    /**
     * check if the ith bit of a is set (1)
     * @param a
     * @param i
     * @return
     */
    private boolean bitSet(int a, int i){
        return (a & 1 << i) != 0;
    }

    /**
     * set i'th bit to 1, (i starts from 0)
     * @param a
     * @param i
     * @return
     */
    private int setBit(int a, int i){
        return a | (1 << i);
    }

    /**
     * set i'th bit to 0, (i starts from 0)
     * @param a
     * @param i
     * @return
     */
    private int resetBit(int a, int i){
        return a & (~(1<<i));
    }
    /**
     * exchane the 1 on ith bith with the 0 on i+1 th bit.
     * @param a
     * @param i
     * @return
     */
    private int convertLarger(int a, int i){
        return setBit( resetBit(a, i), i +1);
    }
    private int convertSmaller(int a, int i) {return resetBit( setBit(a, i), i +1);}
}
