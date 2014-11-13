package ch2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Stack;

/**
 * Created by Jun on 11/8/2014.
 * exercise 2.5
 * This class offers functions to add two linked lists (each node representing one digit)
 * Assuming the list is only singly linked
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
        firstNum.appendToTail(7).appendToTail(1).appendToTail(8);
        LinkedListNode<Integer> secondNum = new LinkedListNode<Integer>();
        secondNum.appendToTail(5).appendToTail(9).appendToTail(2);
        System.out.println("Inverse addition: " + new ListNumberAddition().addListNumbersReverse(firstNum, secondNum));
        System.out.println("Forward addition: " + new ListNumberAddition().addListNumbersForward(firstNum, secondNum));
    }


    private LinkedListNode addListNumbersReverse(LinkedListNode<Integer> a, LinkedListNode<Integer> b){
        if(a == null && b == null){
            return null;
        }
        if(a == null){
            return b;
        }else if(b == null){
            return a;
        }

        int sum = (a.data + b.data)%10;
        LinkedListNode listSum = new LinkedListNode(sum);
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
            listSum.appendToTail(addRemainsReverse(runnerA, carryOver));
        }else if (runnerB != null){
            listSum.appendToTail(addRemainsReverse(runnerB, carryOver));
        }else if(carryOver != 0){//if a and b are of the same length and there is carry over left--sum longer than a/b--add one more node
            listSum.appendToTail(carryOver);
        }
        return listSum;
    }

    private LinkedListNode addListNumbersForward(LinkedListNode<Integer> a, LinkedListNode<Integer> b) {
        if(a == null && b == null){
            return null;
        }
        if(a == null){
            return b;
        }else if(b == null){
            return a;
        }
        //get the number into Stack form
        Stack<Integer> A = new Stack<Integer>();
        Stack<Integer> B = new Stack<Integer>();
        Stack<Integer> sumStack = new Stack<Integer>();
        LinkedListNode<Integer> runnerA = a;
        LinkedListNode<Integer> runnerB = b;

        while (runnerA != null){
            A.push(runnerA.data);
            runnerA = runnerA.next;
        }
        while (runnerB != null){
            B.push(runnerB.data);
            runnerB = runnerB.next;
        }
        //add in Stack form
        int numA = A.pop();
        int numB = B.pop();
        int sum = (numA + numB)%10;
        sumStack.push(sum);
        int carryOver = (numA + numB)/10;
        while (!A.isEmpty() && !B.isEmpty()){
            numA = A.pop();
            numB = B.pop();
            sum = (numA + numB + carryOver)%10;
            carryOver = (numA + numB + carryOver)/10;
            sumStack.push(sum);
        }
        if(!A.isEmpty()){
            addRemainsForward(sumStack, A, carryOver);
        }else if(!B.isEmpty()){
            addRemainsForward(sumStack, B, carryOver);
        }else if(carryOver != 0){
            sumStack.push(carryOver);
        }
        //transfer back into LinkedList form
        LinkedListNode<Integer> sumList = new LinkedListNode<Integer>(sumStack.pop());
        while (!sumStack.isEmpty()){
            sumList.appendToTail(sumStack.pop());
        }
        return sumList;
    }

    private Stack<Integer> addRemainsForward(Stack<Integer> stack, Stack<Integer> a, int carryOver){
        int num = a.pop();
        int sum = (num + carryOver)%10;
        stack.push(sum);
        int carry = (num + carryOver)/10;
        while (!stack.isEmpty()){
            num = a.pop();
            sum = (num + carryOver)%10;
            carry = (num + carryOver)/10;
            stack.push(sum);
        }
        if (carry != 0){
            stack.push(carry);
        }
        return stack;
    }


    //find the last-but-tail node
    //todo -- this function is not used
    private LinkedListNode findTailNode(LinkedListNode a, int offset){
        if(offset < 0){
            throw new InputMismatchException();
        }
        LinkedListNode runner = a;
        LinkedListNode tail = a;
        //set runner 'tail' steps ahead of a;
        for(int i = 0; i < offset; i ++){
            if(runner.next != null){
                runner = runner.next;
            }else {
                return null;
            }
        }
        while (runner.next != null){
            runner = runner.next;
            tail=tail.next;
        }
        return tail;
    }



    //handles the situation when one list is longer than the other;
    //the longer list should add the carryover
    private LinkedListNode addRemainsReverse(LinkedListNode<Integer> a, int carryOver){
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
        if(carry != 0){
            listSum.appendToTail(carry);
        }
        return listSum;
    }

}
