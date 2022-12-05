import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SudoValidatorMain {
    private static final Logger LOGGER = Logger.getLogger(SudoValidatorMain.class.getName());

    public static void main(String[] args) {
         // loop through all arguments and print it to the user
        for (String str : args) {
            try {
                if(args.length != 1) {
                   System.out.println("Please enter correct number of arguments : Sudoku file name");
                }

                int[][] solution = SuducoFileExtractor.getSolutionFromFile(args[0]);
                SUDOKUValidator.isSudokuValid(solution);

            } catch (SudoException | Exception e){
                LOGGER.log(Level.SEVERE, "Exception occured", e);
               System.exit(1);
            }
        }
    }


}



