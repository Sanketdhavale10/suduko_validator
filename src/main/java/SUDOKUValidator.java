import java.util.HashSet;


/**
 * Validates a `int[][]` representing provided sudoku puzzle solution.
 *
 * @param 'int[][]' sudoku file matrix of the solution
 * @return `boolean`
 */
public class SUDOKUValidator {

    public static boolean isSudokuValid(int[][] sudoku){

        boolean result = true;
        //validate rows
        for (int i = 0; i <9 ; i++)
            result &= isRowValid(0, sudoku);

        //validate columns
        for (int i = 0; i <9 ; i++)
            result &= isColumnValid(0, sudoku);

        //validate 3x3 matrix
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j <3 ; j++) {
                result &= isMatrixValid(sudoku, i, j);
            }
        }

        if(result)
            System.out.println("Given SUDOKU is valid");
        else
            System.out.println("Given SUDOKU is not valid");
        return  result;
    }

    private static boolean isRowValid(int row, int[][] sudoku){
        HashSet<Integer> set = getFreshSet();
        for (int col = 0; col <9 ; col++) {
            if(set.contains(sudoku[row][col]))
                set.remove(sudoku[row][col]);
        }
        if(set.size()>0)
            return false;
        return true;
    }

    private static boolean isColumnValid(int col, int[][] sudoku){
        HashSet<Integer> set = getFreshSet();
        for (int row = 0; row <9 ; row++) {
            if(set.contains(sudoku[row][col]))
                set.remove(sudoku[row][col]);
        }
        if(set.size()>0)
            return false;
        return true;
    }

    private static boolean isMatrixValid(int[][] sudoku, int row_index, int col_index){
        int rowStart = row_index*3;
        int colStart = col_index*3;
        HashSet<Integer> set = getFreshSet();
        for (int row = rowStart; row <rowStart+3 ; row++) {
            for (int col = colStart; col <colStart+3 ; col++) {
                if(set.contains(sudoku[row][col]))
                    set.remove(sudoku[row][col]);
            }
        }
        if(set.size()>0)
            return false;
        return true;
    }

    private static HashSet<Integer> getFreshSet(){
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(6);
        set.add(7);
        set.add(8);
        set.add(9);
        return set;
    }

}