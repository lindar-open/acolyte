package lindar.acolyte.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NumbersAcolyte {

    public static boolean nullOrZero(Number number) {
        return number == null || number.doubleValue() == 0d;
    }
    
    public static boolean nullEqualOrLessThanZero(Number number) {
        return number == null || number.doubleValue() <= 0d;
    }
    
    
    public static boolean greaterThanZero(Number number) {
        return number != null && number.doubleValue() > 0d;
    }
    
    public static boolean equalOrGreaterThanZero(Number number) {
        return number != null && number.doubleValue() >= 0d;
    }
    
    public static boolean greaterThan(Number initialNumber, Number comparingNumber) {
        return initialNumber != null && initialNumber.doubleValue() > comparingNumber.doubleValue();
    }
    
    public static boolean equalOrGreaterThan(Number initialNumber, Number comparingNumber) {
        return initialNumber != null && initialNumber.doubleValue() >= comparingNumber.doubleValue();
    }
    
    
    public static boolean lessThanZero(Number number) {
        return number != null && number.doubleValue() < 0d;
    }
    
    public static boolean equalOrLessThanZero(Number number) {
        return number != null && number.doubleValue() <= 0d;
    }
    
    public static boolean lessThan(Number initialNumber, Number comparingNumber) {
        return initialNumber != null && initialNumber.doubleValue() < comparingNumber.doubleValue();
    }
    
    public static boolean equalOrLessThan(Number initialNumber, Number comparingNumber) {
        return initialNumber != null && initialNumber.doubleValue() <= comparingNumber.doubleValue();
    }
    
    
    public static Number defaultIfNull(Number number, Number defaultVal) {
        return number == null ? defaultVal : number;
    }
    
    public static Number defaultIfNullOrZero(Number number, Number defaultVal) {
        return number == null || number.doubleValue() == 0d ? defaultVal : number;
    }
    
    public static Number defaultIfNullEqualOrLessThanZero(Number number, Number defaultVal) {
        return number == null || number.doubleValue() <= 0d ? defaultVal : number;
    }
}