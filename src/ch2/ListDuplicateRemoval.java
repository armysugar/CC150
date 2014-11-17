package ch2;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Jun on 11/3/2014.
 */
public class ListDuplicateRemoval {
    private final LinkedList<Integer> test1 = new LinkedList<Integer>();
    private final LinkedList<String>  test2 = new LinkedList<String>();
    private final LinkedList<ArrayList<Integer>> test3 = new LinkedList<ArrayList<Integer>>();
    {
        test1.add(1);
        test1.add(1);
        test1.add(2);
        test1.add(1);

        test2.add("first");
        test2.add("second");
        test2.add("first");

    }
    public static void main(String[] args) {
        ListDuplicateRemoval ld = new ListDuplicateRemoval();

        System.out.println(ld.removeDuplicates(ld.test1));
        System.out.println(ld.removeDuplicates(ld.test2));
        System.out.println(ld.removeDuplicatesWithoutExtra(ld.test1));
        System.out.println(ld.removeDuplicatesWithoutExtra(ld.test2));
//        System.out.println(ld.removeDuplicates(ld.test3));

    }
    private <E> LinkedList<E> removeDuplicates(LinkedList<E> l){
//        ArrayList a = new ArrayList();
        Hashtable<E, Integer> exist = new Hashtable<E, Integer>();
        for(E e : l){
            if(!exist.contains(e)){
                exist.put(e, 1);
            }
        }
        return new LinkedList<E>(exist.keySet());
    }

    private <E> LinkedList<E> removeDuplicatesWithoutExtra(LinkedList<E> ls){
//        LinkedListNode;
        if(ls.size() < 1){
            return ls;
        }
        E last;
        E runner;
        for(int i = 0; i < ls.size()-1; i++){
            Iterator it = ls.listIterator(i);
            last = (E)it.next();
            while (it.hasNext()){
                if(last.equals(runner = (E) it.next())){
                    it.remove();
                }
            }
//            for(int j = i; j< l.size()-1; j++){
//                runner = l.get(j)
//                if(runner.equals(runner.) )
//
//            }
        }
        return ls;
    }
}
