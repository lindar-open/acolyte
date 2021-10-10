package lindar.acolyte;

import lindar.acolyte.util.NumberFormatAcolyte;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberFormatAcolyteTest {

    @Test
    public void testZeroesMethods(){
        Number test1 = new Integer(10000);
        Number test2 = new BigDecimal(15);

        String result1 = NumberFormatAcolyte.builder().showThousandsSeparator().format(test1);
        String result4 = NumberFormatAcolyte.builder().showThousandsSeparator().hideThousandsSeparator().format(test1);
        String result2 = NumberFormatAcolyte.builder().minFractionDigits(3).maxFractionDigits(5).showTrailingZeros().format(test2);
        String result3 = NumberFormatAcolyte.builder().minFractionDigits(5).maxFractionDigits(5).showTrailingZeros().format(test2);
        String result5 = NumberFormatAcolyte.builder().minFractionDigits(5).showTrailingZeros().hideTrailingZeros().format(test2);

        assertEquals("10,000", result1);
        assertEquals("10000", result4);
        assertEquals("15.000", result2);
        assertEquals("15.00000", result3);
        assertEquals("15", result5);
    }

    @Test
    public void testCurrencyMethods(){
        Number test1 = new BigDecimal(10.99);
        Number test2 = new Integer(10);
        Number test3 = new BigDecimal(10.999);

        String result1 = NumberFormatAcolyte.builder().showCurrency().format(test1);
        String result2 = NumberFormatAcolyte.builder().showCurrency().format(test2);
        String result3 = NumberFormatAcolyte.builder().locale(Locale.US).showCurrency().format(test1);
        String result4 = NumberFormatAcolyte.builder().locale(Locale.US).showCurrency().format(test2);
        String result5 = NumberFormatAcolyte.builder().locale(Locale.US).showTrailingZeros().showCurrency().format(test2);
        String result6 = NumberFormatAcolyte.builder().showTrailingZeros().showCurrency().format(test2);
        String result7 = NumberFormatAcolyte.builder().showCurrency().format(test3);
        String result8 = NumberFormatAcolyte.builder().showCurrency().hideCurrency().format(test1);


        assertEquals("£10.99", result1);
        assertEquals("£10", result2);
        assertEquals("£10.00", result6);
        assertEquals("$10.99", result3);
        assertEquals("$10", result4);
        assertEquals("$10.00", result5);
        assertEquals("£11.00", result7);
        assertEquals("10.99", result8);
    }

    @Test
    public void testRoundingMethods(){
        Number test1 = new BigDecimal("1.77");
        Number test2 = new BigDecimal("1.22");
        Number test3 = new BigDecimal("1.55");
        Number test4 = new BigDecimal("-1.33");
        Number test5 = new BigDecimal("-1.88");
        Number test6 = new BigDecimal("-1.55");


        String[] results1 = generateTestStrings(test1);
        String[] results2 = generateTestStrings(test2);
        String[] results3 = generateTestStrings(test3);
        String[] results4 = generateTestStrings(test4);
        String[] results5 = generateTestStrings(test5);
        String[] results6 = generateTestStrings(test6);

        String roundModeResult = NumberFormatAcolyte.builder().fractionDigits(1).roundingMode(RoundingMode.UP).format(test1);

        //0default(half-up) - 1up - 2down - 3halfDown - 4halfUp
        assertEquals("1.8", results1[0]);
        assertEquals("1.8", results1[1]);
        assertEquals("1.7", results1[2]);
        assertEquals("1.8", results1[3]);
        assertEquals("1.8", results1[4]);

        assertEquals(results1[0], roundModeResult);

        assertEquals("1.2", results2[0]);
        assertEquals("1.3", results2[1]);
        assertEquals("1.2", results2[2]);
        assertEquals("1.2", results2[3]);
        assertEquals("1.2", results2[4]);

        assertEquals("1.6", results3[0]);
        assertEquals("1.6", results3[1]);
        assertEquals("1.5", results3[2]);
        assertEquals("1.5", results3[3]); //
        assertEquals("1.6", results3[4]);

        assertEquals("-1.3", results4[0]);
        assertEquals("-1.4", results4[1]);
        assertEquals("-1.3", results4[2]);
        assertEquals("-1.3", results4[3]);
        assertEquals("-1.3", results4[4]);

        assertEquals("-1.9", results5[0]);
        assertEquals("-1.9", results5[1]);
        assertEquals("-1.8", results5[2]);
        assertEquals("-1.9", results5[3]);
        assertEquals("-1.9", results5[4]);

        assertEquals("-1.6", results6[0]);
        assertEquals("-1.6", results6[1]);
        assertEquals("-1.5", results6[2]);
        assertEquals("-1.5", results6[3]);
        assertEquals("-1.6", results6[4]); //
    }

    @Test
    public void testPrefixSuffixMethods(){
        Number test1 = new Integer("51");
        String testPrefix = "Test:";
        String testSuffix = " Number";

        String result1 = NumberFormatAcolyte.builder().prefix(testPrefix).format(test1);
        String result2 = NumberFormatAcolyte.builder().suffix(testSuffix).format(test1);
        String result3 = NumberFormatAcolyte.builder().prefix(testPrefix).suffix(testSuffix).format(test1);

        assertEquals("Test:51", result1);
        assertEquals("51 Number", result2);
        assertEquals("Test:51 Number", result3);
    }

    @Test
    public void testFormatPatternMethod(){
        String format1 = "#,###,###.000000";
        String format2 = "Number: #.#####";

        Number test1 = new BigDecimal("2353453.56389");

        String result1 = NumberFormatAcolyte.builder().pattern(format1).format(test1);
        String result2 = NumberFormatAcolyte.builder().pattern(format2).format(test1);

        assertEquals("2,353,453.563890", result1);
        assertEquals("Number: 2353453.56389", result2);
    }

    @Test
    public void testPenniesMethod(){
        Number test1 = new BigDecimal("0.95");
        Number test2 = new BigDecimal("1.95");
        Number test3 = new Integer(1);

        String result1 = NumberFormatAcolyte.builder().showPenniesBelowOne().format(test1);
        String result4 = NumberFormatAcolyte.builder().showPenniesBelowOne().hidePenniesBelowOne().format(test1);
        String result2 = NumberFormatAcolyte.builder().showPenniesBelowOne().format(test2);
        String result3 = NumberFormatAcolyte.builder().showPenniesBelowOne().format(test3);

        assertEquals("95p", result1);
        assertEquals("1.95", result2);
        assertEquals("1", result3);
        assertEquals("0.95", result4);
    }

    @Test
    public void testDefaultMethods(){
        Number test1 = new BigDecimal("1.3");
        Number test2 = null;
        String defaultString = "default";

        String result1 = NumberFormatAcolyte.builder().formatOrDefault(test1, defaultString);
        String result2 = NumberFormatAcolyte.builder().formatOrDefault(test2, defaultString);

        String result3 = NumberFormatAcolyte.builder().formatToNull(test1);
        String result4 = NumberFormatAcolyte.builder().formatToNull(test2);

        assertEquals("1.30", result1);
        assertEquals(defaultString, result2);

        assertEquals("1.30", result3);
        assertNull(result4);
    }


    public String[] generateTestStrings(Number num){
        String[] results = new String[5];

        results[0] = NumberFormatAcolyte.builder().fractionDigits(1).format(num);
        results[1] = NumberFormatAcolyte.builder().roundUp().fractionDigits(1).format(num);
        results[2] = NumberFormatAcolyte.builder().roundDown().fractionDigits(1).format(num);
        results[3] = NumberFormatAcolyte.builder().roundHalfDown().fractionDigits(1).format(num);
        results[4] = NumberFormatAcolyte.builder().roundHalfUp().fractionDigits(1).format(num);

        return results;
    }

}
