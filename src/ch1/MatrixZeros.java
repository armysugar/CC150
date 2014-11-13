package ch1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Jun on 10/30/2014.
 */
public class MatrixZeros {

    private final int[][] test1 = {{1,0,0},{0,1,1},{1,0,0}};
    private final int[][] test2 = {{1,1},{2,3}};
    private final int[][] test3 = {{1,2,3},{5,0,1},{8,9,1}};


    public static void main(String[] args) {
        MatrixZeros mz = new MatrixZeros();
//        System.out.println(mz.cleanMatrix(mz.test1)[0][0]);
//        System.out.println(mz.cleanMatrix(mz.test2)[0][0]);
        mz.printArray(mz.cleanMatrix(mz.test1));
        mz.printArray(mz.cleanMatrix(mz.test2));
        mz.printArray(mz.cleanMatrix(mz.test3));

    }

//    private void setZeros(int[][] m) {
//        ArrayList<Integer> colloums = new ArrayList();
//        ArrayList<Integer> zeroColloums = new ArrayList<Integer>();
//        ArrayList<Integer> rows = new ArrayList<Integer>();
//        ArrayList<Integer> zeroRows = new ArrayList<Integer>();
//
//        for(int i = 0; i < m.length; i ++){
//            rows.add(i);
//        }
//        for (int j = 0; j < m[1].length; j++){
//            colloums.add(j);
//        }
//        Iterator<Integer> row = rows.iterator();
//        Iterator<Integer> colloum = colloums.iterator();
////        for(int i = row.next(); row.hasNext(); i = row.next()){
//        for (int i; row.hasNext(); ){
//            i = row.next();
////            row.
////            for(int j = colloum.next(); colloum.hasNext(); j = colloum.next()){
//            for (int j; colloum.hasNext(); ){
//                j = colloum.next();
//                if( 0 == m[i][j] ){
//                    row.remove();
//                    colloum.remove();
//                    zeroColloums.add(j);
//                    zeroRows.add(i);
////                    i = row.next();
////                    j = colloum.next();
//                }
//            }
//        }
//        setZero(m, zeroRows, zeroColloums);
//
//    }

    private int[][] cleanMatrix(int[][] matrix){
//        System.out.println("entering : cleanMatrix.MatrixZeros");
        ArrayList<ArrayList<Integer>> mtr = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int j =0; j < matrix[0].length; j ++){
            row.add(j);
        }
        for(int i = 0; i < matrix.length; i++){
            mtr.add(row); //todo check if this is a shallow copy -- problem if yes
        }
//        System.out.println("got matrix : cleanMatrix.MatrixZeros");
        //get seeds, which has a max number of min( row, column);
        ArrayList<Seed> seeds = new ArrayList<Seed>();
        for(int i = mtr.size()-1; i >= 0; i--){
//            System.out.println("i :" + i +" cleanMatrix.MatrixZeros");
            row = mtr.get(i);
            for(int j = row.size()-1; j >= 0; j--){
//                System.out.println("j : " + j +" cleanMatrix.MatrixZeros");
                if (0 == matrix[i][row.get(j)]){
//                    System.out.println("shringk : cleanMatrix.MatrixZeros");
                    seeds.add(new Seed(i, row.get(j)));
                    shrink(mtr, i, row.get(j));
//                    System.out.println("add seed: cleanMatrix.MatrixZeros");
                    break;
                }
//                System.out.println("got i j : cleanMatrix.MatrixZeros");
            }
            //if got all seeds -- enough to set whole matrix to zeros, break
            if(seeds.size() > mtr.size() || seeds.size() > mtr.get(0).size()){
                break;
            }
        }
        matrix  = setZero(matrix, seeds);
        System.out.println("done !");
        return matrix;
    }

    private void shrink(ArrayList<ArrayList<Integer>> mtr, int row, int colloumn){
        if (row >= mtr.size() || colloumn >= mtr.get(0).size()){
            return;
        }
        for(int i = row-1; i >= 0; i--){
            mtr.get(i).remove(new Integer(colloumn));
        }
    }


    private void setZero(int[][] matrix, ArrayList<Integer> zeroRows, ArrayList<Integer> zeroColloums){
        for(Integer i : zeroRows){
            for(Integer j : matrix[i]){
                j = 0;
            }
        }
        for(Integer j : zeroColloums){
            for(int i = 0; i < matrix.length; i++){
                matrix[i][j] = 0;
            }
        }
    }

    private int[][] setZero(int[][] matrix, ArrayList<Seed> seeds){
        for(Seed s : seeds){
            for(int i = 0; i < matrix.length; i ++){
                matrix[i][s.colloum] = 0;
            }
            for(int j = 0; j < matrix[0].length; j ++){
                matrix[s.row][j] = 0;
            }
        }
        return matrix;
    }

    private void printArray(int[][] m){
        System.out.println("------------------------");
        for(int[] row : m){
            String r = "";
            for(int num : row){
                r += num + " ";
            }
            System.out.println(r);
        }
        System.out.println("========================");
    }

    private class Seed{
        int row;
        int colloum;
        public Seed(int row, int colloum){
            this.row = row;
            this.colloum = colloum;
        }
    }
}
