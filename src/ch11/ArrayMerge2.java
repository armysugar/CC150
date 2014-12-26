package ch11;

import languageTest.IntArrayPrinter;

import java.util.ArrayList;

/**
 * Created by Jun on 12/26/2014.
 *
 * Problem: merge two sorted arrays
 *
 *
 */
public class ArrayMerge2 {

    public static void main(String[] args) {
        int[] recipient = {1,3,4,7,9,12,0,0,0,0,0,0};
        int[] offer = {2,5,10,0,0};
        ArrayMerge2 merger = new ArrayMerge2();
        IntArrayPrinter printer = new IntArrayPrinter();
        merger.mergeArrays(recipient,offer);
        printer.printInts(recipient);
     }

    boolean mergeArrays(int[] recipient, int[] offer){
        if(recipient == null || offer == null || recipient.length == 0 || recipient.length ==0) return false;
        int runnerRecipient = findEffectiveTail(recipient);
        int runnerOffer = findEffectiveTail(offer);
        while (runnerOffer >= 0 && runnerRecipient >= 0) {
            if (recipient[runnerRecipient] < offer[runnerOffer]) {
                recipient[runnerRecipient + runnerOffer] = offer[runnerOffer--];
            } else {
                int cut = findCut(recipient, offer, runnerRecipient, runnerOffer);
                shiftArray(recipient, cut + 1, runnerRecipient, runnerOffer + 1);
                recipient[cut + runnerOffer + 1] = offer[runnerOffer];
                runnerRecipient = cut;
                runnerOffer--;
            }
        }
        if(runnerOffer >= 0){
            inserRemains(recipient, offer, runnerOffer);
        }
        return true;
    }

    int findEffectiveTail(int[] array){
        for(int i = array.length-1; i >=0; i --){
            if(array[i] != 0) return i;
        }
        return -1;
    }

    /**
     * find the index that is smaller thant offer[offerIndex], so that recipient could be shifted
     * @param recipient the array to be inserted into
     * @param offer the array to be inserted
     * @param offerIndex th index in offer to in concern
     * @return the index in recipient that recipient[index] starts to be smaller thant offer[offerIndex], -1
     * if recipient[0] >= offer[offerIndex], -2 if recipient[cutTail] < offer[offerIndex]
     */
    int findCut(int[] recipient, int[] offer, int cutTail, int offerIndex){
        if(recipient[cutTail] < offer[offerIndex]) return -2;
        int cutHead = cutTail;
        while (cutHead >= 1){
            if(recipient[--cutHead] < offer[offerIndex]) return cutHead;
        }
        return -1;
    }

    /**
     * shift the subarray of recipient distance to the right, starting (including) shiftstart to (including) shiftend,
     * @param recipient
     * @param shiftStart
     * @param shiftEnd
     * @param distance
     * @return true if success false if recipient.length < shiftEnd + distance || shiftStart < 0
     */
    boolean shiftArray(int[] recipient, int shiftStart, int shiftEnd, int distance){
        if(recipient.length < shiftEnd + distance ||
                shiftStart < 0) return false;

        for(int i = shiftEnd; i >= shiftStart; i --){
            recipient[i+distance] = recipient[i];
        }
        return true;
    }
    /**
     * insert offer into recipient, starting from 0 to index
     * @param recipient the array to be inserted into
     * @param offer the array to be inserted
     * @param index the index of the last element to be inserted
     * @return true if recipent has enought "0"s at the head to accept offer
     */
    boolean inserRemains(int[] recipient, int[] offer, int index){
        //check if enough 0s exist
        return false;

    }
}
