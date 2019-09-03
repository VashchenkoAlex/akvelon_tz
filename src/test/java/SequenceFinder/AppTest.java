package SequenceFinder;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

/**
 * Test class for SequenceFinder.App
 */
public class AppTest 
{
    /**
     * Method tests valid values for SequenceFinder.App
     *
     */
    @Test
    public void testValidMatrix() throws ValidationException {
        test("01011,11111,01110",5);
        test("01011,00000,01110",3);
        test("01010,10101,01010",1);
        test("00000,00000,00000",0);
        test("0,0,0",0);
        test("1,1,1",3);
        test("1,0,1",1);
        test("1,1,0",2);
        test("0,1,1",2);

        test("0001000,0001000,0001000",3);
        test("01101,11110,10101,01110",4);
        test("01101,11110,10101,10101,01110",5);
        test("1011111101,0101111101,1000100001,1000100001,1000100001",6);
        test("1010111101,0101111101,0101111101,1000100001,1000100001,1000100001",6);
        test("111110111111,000101111101,010111110100,100010000100,100010000100,100010000100",6);
        test("000101111101,111110111111,010111110100,100010000100,100010000100,100010000100",6);
        test("000101111101,010111110100,111110111111,100010000100,100010000100,100010000100",6);
        test("000101111101,010111110100,100010000100,111110111111,100010000100,100010000100",6);
        test("000101111101,010111110100,100010000100,100010000100,111110111111,100010000100",6);
        test("000101111101,010111110100,100010000100,100010000100,100010000100,111110111111",6);

        test("100000000000,100000000000,100000000000,100000000000,100000000000,100000000000",6);
        test("010000000000,010000000000,010000000000,010000000000,010000000000,010000000000",6);
        test("000000000010,000000000010,000000000010,000000000010,000000000010,000000000010",6);
        test("000000000011,000000000011,000000000011,000000000011,000000000011,000000000011",6);

        test("100000000000,010000000000,001000000000,000100000000,000010000000,000001000000,000000100000,000000010000,000000001000,000000000100,000000000010,000000000001",1);
        test("000000000001,000000000010,000000000100,000000001000,000000010000,000000100000,000001000000,000010000000,000100000000,001000000000,010000000000,100000000000",1);

        test("111111110,111111101,111111011,111110111,111101111,111011111,110111111,101111111,011111111",8);
        test("011111111,101111111,110111111,111011111,111101111,111110111,111111011,111111101,111111110",8);
    }

    /**
     * Method tests invalid values and wrong matrix size for SequenceFinder.App
     *
     */
    @Test
    public void testWrongMatrix(){
        test("0,,0",ValidationExceptionsType.WRONG_MATRIX_SIZE_EXCEPTION);
        test("1,,1",ValidationExceptionsType.WRONG_MATRIX_SIZE_EXCEPTION);

        test("101010101010,101010101010,1",ValidationExceptionsType.WRONG_MATRIX_SIZE_EXCEPTION);
        test("101010101010,1,101010101010",ValidationExceptionsType.WRONG_MATRIX_SIZE_EXCEPTION);
        test("1,101010101010,101010101010",ValidationExceptionsType.WRONG_MATRIX_SIZE_EXCEPTION);

        test("0101,0101,",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test(",,",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test("",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test(" ",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test(" , , ",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test("  ,  ,  ",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test("   ,   ,   ,  ",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test(",0,0",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test("1,1,",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test(",1,1",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test("0,0,",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);

        test("121,101,101",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test("101,131,101",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test("101,101,141",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);

        test("101.101,101",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test("101,101.101",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);

        test("a01,101,101",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test("101,1b1,101",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
        test("101,101,10C",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);

        test(",0101,",ValidationExceptionsType.WRONG_VALUE_EXCEPTION);
    }

    /**
     * Method calls App.runApp() method with given expression
     * and asserts received result with given expected result
     *
     * @param expression - given String expression
     * @param result - given expected result
     * @throws ValidationException if expression has wrong data or parsed matrix is incorrect
     */
    private void test(String expression, int result) throws ValidationException {

        assertEquals(result, App.runApp(expression) );
    }

    /**
     * Method calls App.runApp() method with given expression
     * and asserts received exception type with given
     * Fails if runApp() was completed without exceptions
     *
     * @param expression - given String expression
     * @param type - given  exception type
     */
    private void test(String expression, ValidationExceptionsType type){
        try{
            App.runApp(expression);
            fail();
        }catch (ValidationException ex){
            assertEquals(type,ex.getType());
        }
    }
}
