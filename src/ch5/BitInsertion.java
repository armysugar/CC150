package ch5;

/**
 * Created by Jun on 12/4/2014.
 *PROBLEM: 5.1 insert bits of int m into int n;
 *
 * HINT: build a band-pass filter, remove n's bits, then OR m's bits there
 * Youaregiventwo32-bitnumbers,NandM,andtwobitpositions,landj.
 * WriteamethodtoinsertMintoNsuchthatMstartsatbitjandendsatbiti.
 * Youcan assumethatthebitsjthroughihaveenoughspacetofitallofM.
 * Thatis,if M=10011 ,youcanassumethatthereareatleast5bitsbetweenjandi.
 * You wouldnot,forexample,havej=3andi=2,becauseMcouldnotfullyfit betweenbit3andbit2.
 */
public class BitInsertion {

    public static void main(String[] args) {
        BitInsertion insertion = new BitInsertion();
        System.out.println(Integer.toBinaryString(insertion.insertBits(0B1000000, 0b111, 3, 5 )));
    }

    //insert m into n, starting from 'start' ending at 'end' (end <- start)
    private int insertBits(int n, int m, int start, int end){
        int leftBand = ~((1 << (end+1)) - 1);
        int rightBand = (1 << (start)) - 1;
        int trench = leftBand | rightBand;

        int cutN = n & trench;

        int insert = cutN | (m << start);

        return insert;
    }

}
