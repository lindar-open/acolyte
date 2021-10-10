package lindar.acolyte;

import lindar.acolyte.util.BigDecimalAcolyte;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BigDecimalAcolyteTest {

    @Test
    public void testDefaultMethods(){
        BigDecimal testCheck = new BigDecimal(10);
        BigDecimal test1 = new BigDecimal(5);
        BigDecimal test2 = new BigDecimal(0);
        BigDecimal test3 = new BigDecimal(-1);
        BigDecimal test4 = null;
        BigDecimal testZero = BigDecimal.ZERO;

        BigDecimal result1 = BigDecimalAcolyte.defaultIfNull(test1);
        BigDecimal result2 = BigDecimalAcolyte.defaultIfNull(test4);

        BigDecimal result3 = BigDecimalAcolyte.defaultIfNull(test1, testCheck);
        BigDecimal result4 = BigDecimalAcolyte.defaultIfNull(test4, testCheck);

        BigDecimal result5 = BigDecimalAcolyte.defaultIfNullOrZero(test1, testCheck);
        BigDecimal result6 = BigDecimalAcolyte.defaultIfNullOrZero(test2, testCheck);
        BigDecimal result7 = BigDecimalAcolyte.defaultIfNullOrZero(test4, testCheck);
        BigDecimal result12 = BigDecimalAcolyte.defaultIfNullOrZero(test3, testCheck);

        BigDecimal result8 = BigDecimalAcolyte.defaultIfNullEqualOrLessThanZero(test1, testCheck);
        BigDecimal result9 = BigDecimalAcolyte.defaultIfNullEqualOrLessThanZero(test2, testCheck);
        BigDecimal result10 = BigDecimalAcolyte.defaultIfNullEqualOrLessThanZero(test3, testCheck);
        BigDecimal result11 = BigDecimalAcolyte.defaultIfNullEqualOrLessThanZero(test4, testCheck);

        assertEquals(test1, result1);
        assertEquals(testZero, result2);

        assertEquals(test1, result3);
        assertEquals(testCheck, result4);

        assertEquals(test1, result5);
        assertEquals(testCheck, result6);
        assertEquals(testCheck, result7);
        assertEquals(test3, result12);

        assertEquals(test1, result8);
        assertEquals(testCheck, result9);
        assertEquals(testCheck, result10);
        assertEquals(testCheck, result11);
    }

}
