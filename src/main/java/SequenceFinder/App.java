package SequenceFinder;

import java.util.regex.Pattern;

import static SequenceFinder.ValidationExceptionsType.*;

/**
 * This app receives a binary matrix and finds the the longest sequence of 1's either row wise or column wise.
 * Receives expression string with the matrix through arguments (String[] args)
 * Rows must be separated by coma
 *
 */
public class App 
{
    /**
     * Symbol that separates rows in given expression
     */
    private static final String SEPARATOR = ",";
    /**
     * Character code for zero
     */
    private static final int ZERO_CHAR_CODE = 48;
    /**
     * The pattern used for expression validation,
     */
    private static final Pattern BINARY_PATTERN = Pattern.compile("^[0-1]+[0-1,]*[0-1]+$");

    /**
     * The biggest sequence size founded at the moment
     */
    private static int biggestSequenceSize;
    /**
     * Boolean flag is sequence was started
     */
    private static boolean isStartSequence;

    /**
     * Start point for the App with arguments
     * @param args - used for receiving expression with a binary matrix
     * Shows result in console
     */
    public static void main( String[] args )
    {
        String expression = args[0];
        String result;

        try {
            result = runApp(expression)+"";
        } catch (ValidationException e) {
            result = e.toString();
        }

        System.out.println(result);
    }


    /**
     * Protected method executes parseMatrix() and findSequence() on the given expression
     * @param expression - given String expression with binary matrix
     * @return int result
     * @throws ValidationException if the given matrix has wrong values or size
     */
    static int runApp(String expression) throws ValidationException {
        return findSequence(parseMatrix(expression));
    }

    /**
     * The method executes validateExpression(), validateMatrixSize()
     * and parse given expression
     * @param expression - given String expression with a binary matrix
     * @return boolean[][] binary matrix
     * @throws ValidationException if the given matrix has wrong values or size
     */
    private static boolean[][] parseMatrix(String expression) throws ValidationException {

        validateExpression(expression);

        String [] rows = expression.split(SEPARATOR);
        validateMatrixSize(rows);

        boolean [][] matrix = new boolean[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            matrix[i] = parseRow(rows[i]);
        }

        return matrix;
    }

    /**
     * The method validates given expression
     * @param expression - given String expression with a binary matrix
     * @throws ValidationException if the given matrix has wrong values
     */
    private static void validateExpression(String expression) throws ValidationException {

        if (!BINARY_PATTERN.matcher(expression).matches()){
            throw new ValidationException(WRONG_VALUE_EXCEPTION);
        }

    }

    /**
     * The method parses given row
     * @param row - given String row
     * @return boolean[] array with parsed row
     */
    private static boolean[] parseRow(String row){
        int rowLength = row.length();
        boolean[] rowValues = new boolean[rowLength];
        for (int j = 0; j < rowLength; j++) {
            rowValues[j] = row.charAt(j) != ZERO_CHAR_CODE;
        }

        return rowValues;
    }

    /**
     * The method validates given expression
     * @param rows - given String[] array with a splitted rows
     * @throws ValidationException if the given matrix has wrong size
     */
    private static void validateMatrixSize(String [] rows) throws ValidationException {

        if (rows.length > 0) {
            int previousRowLength = rows[0].length();
            int currentRowLength;
            for (String row : rows) {
                currentRowLength = row.length();
                if (currentRowLength != previousRowLength || currentRowLength == 0) {
                    throw new ValidationException(WRONG_MATRIX_SIZE_EXCEPTION);
                }
            }
        } else {
            throw new ValidationException(WRONG_MATRIX_SIZE_EXCEPTION);
        }
    }

    /**
     * The method seeks out the biggest sequence in the given matrix
     * @param matrix - given boolean[][] binary matrix
     * @return the biggest sequence
     */
    private static int findSequence(boolean[][] matrix){
        int currentSequenceSize = 0;
        biggestSequenceSize = 0;
        for (boolean [] row: matrix) {
            isStartSequence = true;
            for (boolean value: row) {
                currentSequenceSize = inspectSequence(value, currentSequenceSize);
            }

        }

        for (int i = 0; i < matrix[0].length; i++) {
            isStartSequence = true;
            for (boolean[] value : matrix) {
                currentSequenceSize = inspectSequence(value[i], currentSequenceSize);
            }
        }

        return biggestSequenceSize;
    }

    /**
     * The method inspects sequence regarding given value from matrix and current sequence size
     * @param value - given boolean value from matrix
     * @param currentSequenceSize - int current sequence size
     * @return inspected current sequence size
     */
    private static int inspectSequence(boolean value, int currentSequenceSize) {
        if (isStartSequence) {
            currentSequenceSize = 0;
        }

        if (value) {
            currentSequenceSize++;
            isStartSequence = false;
        } else {
            isStartSequence = true;
        }

        if (currentSequenceSize > biggestSequenceSize) {
            biggestSequenceSize = currentSequenceSize;
        }

        return currentSequenceSize;
    }
}
