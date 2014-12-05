package ch5;

/**
 * Created by Jun on 12/4/2014.
 *
 * WriteafunctiontodeterminethenumberofbitsrequiredtoconvertintegerA tointegerB
 */
public class BitConversion {

    public static void main(String[] args) {
        BitConversion bitCon = new BitConversion();
        System.out.println(bitCon.bitDifference(31,8));
    }

    //count the number of differen bits in two numbers
    private int bitDifference(int a, int b){
        return bitCount(a^b);
    }

    //count the number of 1's in a binary number
    private int bitCount(int i){
        int count = 0;
        while (i != 0){
            count += i%2;
            i /= 2;
        }
        return count;
    }
}
