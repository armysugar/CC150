package ch9;

import java.util.*;

/**
 * Created by Jun on 12/7/2014.
 *
 * PROBLEM: 9.9 find all ways to put 8 queens on board without conflict
 *
 *
 */
public class EightQueens {

    public static void main(String[] args) {
        EightQueens board  = new EightQueens();
        board.printBoard(8);
    }

    public void printBoard(int size){
        HashMap<LinkedList, LinkedList> base = new HashMap<>();
        LinkedList oneLine = new LinkedList();
        ArrayList<HashMap> bases = new ArrayList<>();

        for(int i = 0; i < size; i ++) oneLine.add(i);
        base.put(null, oneLine);
        bases.add(base);

        printBoard(size, 1, bases);
    }

    private void printBoard(int size, int currentLine, ArrayList<HashMap> bases){
        HashMap currentBase = bases.get(bases.size()-1);
        HashMap newBase = new HashMap();
        Set<LinkedList> patterns = currentBase.keySet();
        for(LinkedList pattern : patterns){
            LinkedList possibleNew = (LinkedList)currentBase.get(pattern);
            for(Object tail : possibleNew){
                LinkedList newPattern = new LinkedList();
                Iterator it = pattern.iterator();
                Object head = it.next();
                while (it.hasNext()){
                    newPattern.add(it.next());
                }
                newPattern.add(tail);
                LinkedList newest = (LinkedList)currentBase.get(newPattern);
                if(newest == null) continue;
//                HashMap newBase = new HashMap();
                LinkedList addPattern = new LinkedList();
                addPattern.addAll(pattern);
                addPattern.add(tail);
                LinkedList newTail = new LinkedList();
                for(Object candidate : newest){
                    if(candidate != pattern.get(0)) { //todo use the right compare
                        newTail.add(candidate);
                    }
                }
                newBase.put(newPattern,newTail);
            }
        }
        bases.add(newBase);
        if(size == currentLine) System.out.println(bases.get(bases.size() -1));
        else printBoard(size, currentLine + 1, bases);
    }
}
