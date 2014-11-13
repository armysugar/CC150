package languageTest;

import java.util.ArrayList;

/**
 * Created by Jun on 10/30/2014.
 */
public class ArrayListTest {
    public static void main(String[] args) {
        //test when remove(int), whether it view int as an index or an object
        ArrayList<Integer> i = new ArrayList<Integer>();
        i.add(1);
        i.add(0);
        i.remove(new Integer(0));
        System.out.println(i);
        // conclusion todo : int would be viewed as index
    }
}
