package ch2;

import java.util.ArrayList;

/**
 * Created by Jun on 11/8/2014.
 */
public class ListNumberAddition {
//    ArrayList<Integer> firstNum = new ArrayList<Integer>();
//    LinkedListNode second_1 = new LinkedListNode(7);
//    LinkedListNode third_1 = new LinkedListNode(7);
//    LinkedListNode first_2 = new LinkedListNode(7);
//    LinkedListNode second_2 = new LinkedListNode(7);
//    LinkedListNode third_2 = new LinkedListNode(7);
    public static void main(String[] args) {
        LinkedListNode<Integer> firstNum = new LinkedListNode<Integer>();
        firstNum.appendToTail(7).appendToTail(1).appendToTail(6);
        LinkedListNode<Integer> secondNum = new LinkedListNode<Integer>();
        secondNum.appendToTail(5).appendToTail(9).appendToTail(2);
        System.out.println(new ListNumberAddition().addListNumbers(firstNum,secondNum));
    }

    private LinkedListNode addListNumbers(LinkedListNode<Integer> a, LinkedListNode<Integer> b){
        if(a == null && b == null){
            return null;
        }
        if(a == null){
            return b;
        }else if(b == null){
            return a;
        }

//        int sum = (a.data+b.data)%10;
//        LinkedListNode<Integer> listSum = new LinkedListNode<Integer>(sum);
//        LinkedListNode runnerA = a.next;
//        LinkedListNode runnerB = b.next;
//        while (a.next != null && b.next != null){
//            listSum.appendToTail()
//        }

        //--------obsolete

        int sum = (a.data + b.data)%10;
        LinkedListNode listSum = new LinkedListNode(sum);
//        listSum.appendToTail(sum);
        int carryOver = (a.data + b.data)/10;
        //for each a+b+carry_over
        LinkedListNode runnerA = a.next;
        LinkedListNode runnerB = b.next;
        while(runnerA != null && runnerB != null){
            sum = ((Integer)runnerA.data + (Integer)runnerB.data + carryOver)%10;
            carryOver = ((Integer)runnerA.data + (Integer)runnerB.data + carryOver) / 10;
            listSum.appendToTail(sum);
            runnerA = runnerA.next;
            runnerB = runnerB.next;
        }
        if(runnerA != null){
            listSum.appendToTail(addRemains(runnerA, carryOver));
        }else if (runnerB != null){
            listSum.appendToTail(addRemains(runnerB, carryOver));
        }
        return listSum;

        //carry_over = sum/10

        //


    }

    private LinkedListNode addRemains(LinkedListNode<Integer> a, int carryOver){
        int sum = (a.data+carryOver)%10;
        int carry = (a.data+carryOver)/10;
        LinkedListNode<Integer> runner = a;
        LinkedListNode<Integer> listSum = new LinkedListNode<Integer>(sum);
        while (runner != null){
            sum = (runner.data+carry)%10;
            carry = (runner.data=carry)/10;
            listSum.appendToTail(sum);
            runner = runner.next;
        }
        return listSum;
    }

}
