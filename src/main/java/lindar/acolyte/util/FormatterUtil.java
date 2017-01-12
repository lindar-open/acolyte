package lindar.acolyte.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

@Slf4j
public class FormatterUtil {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");
    private static final DecimalFormat DECIMAL_FORMAT_NO_EXTRA_DIGITS = new DecimalFormat("#,##0.##");

    private static final String POUND_SIGN = "£";
    private static final String PENNY_SIGN = "p";

    public static String formatNumber(Number number) {
        if (number == null) {
            return NumberUtils.DOUBLE_ZERO.toString();
        }
        if (number.doubleValue() % 1 == 0) {
            return DECIMAL_FORMAT_NO_EXTRA_DIGITS.format(number);
        }
        return remove0Decimals(DECIMAL_FORMAT.format(number));
    }

    public static String formatNumber(String prefix, Number number) {
        return prefix + formatNumber(number);
    }

    public static String formatNumber(Number number, String suffix) {
        return formatNumber(number) + suffix;
    }

    private static String remove0Decimals(String number) {
        if (number.contains(".00") || number.contains(".0")) {
            return number.substring(0, number.indexOf('.'));
        }
        return number;
    }

    public static String formatMoney(Number amount) {
        double amountDoubleVal = amount.doubleValue();
        if (amountDoubleVal == 0) {
            return "0";
        }
        if (amountDoubleVal >= 1) {
            return POUND_SIGN + formatNumber(amount);
        }
        return PENNY_SIGN + DECIMAL_FORMAT.format(amountDoubleVal * 100);
    }

    public static String formatNumber(String amount) {
        if (!NumberUtils.isCreatable(amount)) {
            return NumberUtils.DOUBLE_ZERO.toString();
        }
        Double amountDoubleVal;
        try {
            amountDoubleVal = DECIMAL_FORMAT.parse(amount).doubleValue();
        } catch (ParseException pe) {
            log.warn("Cannot parse number: {} | {}", amount, pe);
            amountDoubleVal = NumberUtils.DOUBLE_ZERO;
        }
        return formatNumber(amountDoubleVal);
    }

    public static String formatMoney(String amount) {
        return POUND_SIGN + formatNumber(amount);
    }

    public static String addAndFormatMoney(String firstAmount, String secondAmount) {
        return POUND_SIGN + addAndFormatNumber(firstAmount, secondAmount);
    }
    
    public static String addAndFormatNumber(String firstAmount, String secondAmount) {
        Double firstAmountDoubleVal;
        Double secondAmountDoubleVal;

        if (NumberUtils.isCreatable(firstAmount)) {
            try {
                firstAmountDoubleVal = DECIMAL_FORMAT.parse(firstAmount).doubleValue();
            } catch (ParseException pe) {
                log.warn("Cannot parse number: {} | {}", firstAmount, pe);
                firstAmountDoubleVal = NumberUtils.DOUBLE_ZERO;
            }
        } else {
            firstAmountDoubleVal = NumberUtils.DOUBLE_ZERO;
        }

        if (NumberUtils.isCreatable(secondAmount)) { 
            try {
                secondAmountDoubleVal = DECIMAL_FORMAT.parse(secondAmount).doubleValue();
            } catch (ParseException pe) {
                log.warn("Cannot parse number: {} | {}", secondAmount, pe);
                secondAmountDoubleVal = NumberUtils.DOUBLE_ZERO;
            }
        } else {
            secondAmountDoubleVal = NumberUtils.DOUBLE_ZERO;
        }
        
        return formatNumber(firstAmountDoubleVal + secondAmountDoubleVal);
    }

    private FormatterUtil() {
    }
}
