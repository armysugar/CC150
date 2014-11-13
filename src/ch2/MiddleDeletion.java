package ch2;

/**
 * Created by Jun on 11/4/2014.
 */
public class MiddleDeletion {
    LinkedListNode<String> list = new LinkedListNode<String>();
//    {
//        list.appendToTail(new LinkedListNode("FIRST"));
//        list.appendToTail(new LinkedListNode("SECOND"));
//        list.appendToTail(new LinkedListNode("THIRD"));
//
//    }
    public static void main(String[] args) {
        LinkedListNode first = new LinkedListNode("FIRST");
        LinkedListNode second = new LinkedListNode("SECOND");
        LinkedListNode third = new LinkedListNode("THIRD");

        first.appendToTail(second).appendToTail(third);
        MiddleDeletion del = new MiddleDeletion();
        del.removeMiddle(second);
        System.out.println(first);
    }
/*
* to remove the middle node, just coppy the data of middle+1 (including middle+1's "next" to the current middle
* */
    private LinkedListNode removeMiddle(LinkedListNode middle){
        if(null == middle || null == middle.next){
            return null;
        }
        LinkedListNode runner = middle;
        System.out.println("1 : removeMiddel.MiddelDeletion");
//        while (null != runner.next){
//            System.out.println("2 : removeMiddel.MiddelDeletion");
//            runner.data = runner.next.data;
//            runner.next = runner.next.next;
//            runner = runner.next;
//            System.out.println("3 " + runner +": removeMiddel.MiddelDeletion");
//        }
        runner.data = runner.next.data;
        runner.next = runner.next.next;
        return middle;
    }
}
