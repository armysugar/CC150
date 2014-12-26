package languageTest;

/**
 * Created by Jun on 12/13/2014.
 */
public class IntArrayPrinter {
    public void printInts(int[] array){
        if(array == null || array.length == 0) return;
        StringBuffer sb = new StringBuffer();
        int high = array.length - 1;
        for(int i = 0; i <= high; i ++){
            sb.append(array[i]);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}

