package lindar.acolyte;

import lindar.acolyte.util.NumbersAcolyte;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberAcolyteTest {

    @Test
    public void testNullCheckMethods(){
        Integer test1 = null;
        int test2 = 0;
        int test3 = 1;
        int test4 = 5;
        int test5 = -1;
        Integer test6 = null;
        int test7 = 1;

        assertTrue(NumbersAcolyte.nullOrZero(test1));
        assertTrue(NumbersAcolyte.nullOrZero(test2));
        assertFalse(NumbersAcolyte.nullOrZero(test3));

        assertTrue(NumbersAcolyte.nullEqualOrLessThanZero(test1));
        assertTrue(NumbersAcolyte.nullEqualOrLessThanZero(test2));
        assertTrue(NumbersAcolyte.nullEqualOrLessThanZero(test5));
        assertFalse(NumbersAcolyte.nullEqualOrLessThanZero(test3));
        assertFalse(NumbersAcolyte.nullEqualOrLessThanZero(test4));

        assertTrue(NumbersAcolyte.nullOrEqual(test1, test6));
        assertTrue(NumbersAcolyte.nullOrEqual(test3, test7));
        assertFalse(NumbersAcolyte.nullOrEqual(test1, test3));
        assertFalse(NumbersAcolyte.nullOrEqual(test3, test2));
    }

    @Test
    public void testEqualsMethods(){
        Integer test1 = null;
        int test2 = 0;
        int test3 = 1;
        int test4 = 5;
        int test5 = 1;

        assertTrue(NumbersAcolyte.equal(test3, test5));
        assertFalse(NumbersAcolyte.equal(test3, test4));

        assertFalse(NumbersAcolyte.notEqual(test3, test5));
        assertTrue(NumbersAcolyte.notEqual(test3, test4));

        assertTrue(NumbersAcolyte.notEqualAndNotNull(test3, test4));
        assertFalse(NumbersAcolyte.notEqualAndNotNull(test1, test2));
        assertFalse(NumbersAcolyte.notEqualAndNotNull(test3, test5));
    }

    @Test
    public void testGreaterThanMethods(){
        Integer test1 = null;
        int test2 = 0;
        int test3 = 1;
        int test4 = 5;
        int test5 = -1;
        int test6 = -6;
        int test7 = 1;

        assertTrue(NumbersAcolyte.greaterThan(test4, test3));
        assertFalse(NumbersAcolyte.greaterThan(test3, test4));

        assertTrue(NumbersAcolyte.greaterThanOne(test4));
        assertFalse(NumbersAcolyte.greaterThanOne(test3));
        assertFalse(NumbersAcolyte.greaterThanOne(test2));
        assertFalse(NumbersAcolyte.greaterThanOne(test1));
        assertFalse(NumbersAcolyte.greaterThanOne(test6));

        assertTrue(NumbersAcolyte.equalOrGreaterThanOne(test4));
        assertTrue(NumbersAcolyte.equalOrGreaterThanOne(test3));
        assertFalse(NumbersAcolyte.equalOrGreaterThanOne(test2));
        assertFalse(NumbersAcolyte.equalOrGreaterThanOne(test1));
        assertFalse(NumbersAcolyte.equalOrGreaterThanOne(test6));

        assertTrue(NumbersAcolyte.equalOrGreaterThan(test4, test3));
        assertFalse(NumbersAcolyte.equalOrGreaterThan(test3, test4));
        assertTrue(NumbersAcolyte.equalOrGreaterThan(test7, test3));
        assertTrue(NumbersAcolyte.equalOrGreaterThan(test2, test5));
        assertTrue(NumbersAcolyte.equalOrGreaterThan(test4, test6));

        assertTrue(NumbersAcolyte.greaterThanZero(test4));
        assertFalse(NumbersAcolyte.greaterThanZero(test2));
        assertFalse(NumbersAcolyte.greaterThanZero(test5));

        assertTrue(NumbersAcolyte.equalOrGreaterThanZero(test4));
        assertTrue(NumbersAcolyte.equalOrGreaterThanZero(test2));
        assertFalse(NumbersAcolyte.equalOrGreaterThanZero(test5));
    }

    @Test
    public void testLessThanMethods(){
        Integer test1 = null;
        int test2 = 0;
        int test3 = 1;
        int test4 = 5;
        int test5 = -1;
        int test6 = -6;
        int test7 = 1;

        assertFalse(NumbersAcolyte.lessThan(test4, test3));
        assertTrue(NumbersAcolyte.lessThan(test3, test4));

        assertFalse(NumbersAcolyte.lessThanOne(test4));
        assertFalse(NumbersAcolyte.lessThanOne(test3));
        assertTrue(NumbersAcolyte.lessThanOne(test2));
        assertFalse(NumbersAcolyte.lessThanOne(test1));
        assertTrue(NumbersAcolyte.lessThanOne(test6));

        assertFalse(NumbersAcolyte.equalOrLessThanOne(test4));
        assertTrue(NumbersAcolyte.equalOrLessThanOne(test3));
        assertTrue(NumbersAcolyte.equalOrLessThanOne(test2));
        assertFalse(NumbersAcolyte.equalOrLessThanOne(test1));
        assertTrue(NumbersAcolyte.equalOrLessThanOne(test6));

        assertFalse(NumbersAcolyte.equalOrLessThan(test4, test3));
        assertTrue(NumbersAcolyte.equalOrLessThan(test3, test4));
        assertTrue(NumbersAcolyte.equalOrLessThan(test7, test3));
        assertFalse(NumbersAcolyte.equalOrLessThan(test2, test5));
        assertFalse(NumbersAcolyte.equalOrLessThan(test4, test6));

        assertFalse(NumbersAcolyte.lessThanZero(test4));
        assertFalse(NumbersAcolyte.lessThanZero(test2));
        assertTrue(NumbersAcolyte.lessThanZero(test5));

        assertFalse(NumbersAcolyte.equalOrLessThanZero(test4));
        assertTrue(NumbersAcolyte.equalOrLessThanZero(test2));
        assertTrue(NumbersAcolyte.equalOrLessThanZero(test5));

        assertTrue(NumbersAcolyte.nullOrLessThan(test1, test3));
        assertTrue(NumbersAcolyte.nullOrLessThan(test5, test3));
        assertTrue(NumbersAcolyte.nullOrLessThan(test2, test4));
        assertTrue(NumbersAcolyte.nullOrLessThan(test6, test5));
        assertFalse(NumbersAcolyte.nullOrLessThan(test4, test3));
    }

    @Test
    public void testDefaultMethods(){
        Integer test1 = null;
        int test2 = 0;
        int test3 = 3;
        int test4 = 2;
        int test5 = -1;

        Number result1 = NumbersAcolyte.defaultIfNull(test1);
        Number result2 = NumbersAcolyte.defaultIfNull(test3);
        Number result3 = NumbersAcolyte.defaultIfNull(test1, test4);
        Number result4 = NumbersAcolyte.defaultIfNull(test2, test4);

        Number result5 = NumbersAcolyte.defaultIfNullOrZero(test1, test4);
        Number result6 = NumbersAcolyte.defaultIfNullOrZero(test2, test4);
        Number result7 = NumbersAcolyte.defaultIfNullOrZero(test3, test4);

        Number result8 = NumbersAcolyte.defaultIfNullEqualOrLessThanZero(test1, test4);
        Number result9 = NumbersAcolyte.defaultIfNullEqualOrLessThanZero(test2, test4);
        Number result10 = NumbersAcolyte.defaultIfNullEqualOrLessThanZero(test3, test4);
        Number result11 = NumbersAcolyte.defaultIfNullEqualOrLessThanZero(test5, test4);

        assertEquals(0, result1.intValue());
        assertEquals(test3, result2.intValue());
        assertEquals(test4, result3.intValue());
        assertEquals(test2, result4.intValue());

        assertEquals(test4, result5.intValue());
        assertEquals(test4, result6.intValue());
        assertEquals(test3, result7.intValue());

        assertEquals(test4, result8.intValue());
        assertEquals(test4, result9.intValue());
        assertEquals(test3, result10.intValue());
        assertEquals(test4, result11.intValue());
    }

}
