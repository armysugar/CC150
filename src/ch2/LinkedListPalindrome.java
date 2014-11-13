package ch2;

/**
 * Created by Jun on 11/12/2014.
 * exercise 2.7
 * This class checks if a linked list is a palindrome
 * Assuming it's doubly linked
 */
public class LinkedListPalindrome {
    public static void main(String[] args) {
        LinkedListNode<Integer> list1 = new LinkedListNode<Integer>();
        list1.appendToTail(1).appendToTail(2).appendToTail(3).appendToTail(2).appendToTail(1);
        LinkedListNode<Integer> list2 = new LinkedListNode<Integer>();

        list2.appendToTail(1).appendToTail(2).appendToTail(3);

        LinkedListNode<Character> list3 = new LinkedListNode<Character>();
        list3.appendToTail('a').appendToTail('b').appendToTail('a');
        LinkedListNode<Character> list4 = new LinkedListNode<Character>();
        list4.appendToTail('a');

        LinkedListPalindrome checker = new LinkedListPalindrome();
        System.out.println(list1 + " " + String.valueOf(checker.isPalindrome(list1)));
        System.out.println(list2 + " " +  String.valueOf(checker.isPalindrome(list2)));
        System.out.println(list3 + " " + String.valueOf(checker.isPalindrome(list3)));
        System.out.println(list4 + " " + String.valueOf(checker.isPalindrome(list4)));
    }

    private boolean isPalindrome(LinkedListNode list){
        //find tail
        LinkedListNode tail = list;
        while (tail.next != null){
            tail = tail.next;
        }
        //check if nodes at symmetric location is equal
        LinkedListNode head = list;
        while (head != tail){
            if(!head.data.equals(tail.data)){
                return false;
            }
            else {
//                System.out.println(tail.data + " " + head.data + " are equal \n");
                head = head.next;
                tail = tail.last;
            }
        }
        return true;
    }
}
