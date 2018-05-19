package lindar.acolyte.util

class NumbersAcolyte {
    companion object {

        @JvmStatic fun nullOrZero(number: Number?): Boolean {
            return number == null || number.toDouble() == 0.0
        }

        @JvmStatic fun nullEqualOrLessThanZero(number: Number?): Boolean {
            return number == null || number.toDouble() <= 0.0
        }

        @JvmStatic fun nullOrEqual(initialNumber: Number?, comparingNumber: Number?): Boolean {
            if (initialNumber == null && comparingNumber == null) {
                return true
            }
            var initial = 0.0
            if (initialNumber != null) {
                initial = initialNumber.toDouble()
            }
            var comparing = 0.0
            if (comparingNumber != null) {
                comparing = comparingNumber.toDouble()
            }
            return initial == comparing
        }

        /**
         * The numbers have to be NON-NULL. Otherwise use the nullOrEqual method
         * @param initialNumber
         * *
         * @param comparingNumber
         * *
         * @return
         */
        @JvmStatic fun equal(initialNumber: Number?, comparingNumber: Number?): Boolean {
            return initialNumber != null && comparingNumber != null
                    && initialNumber.toDouble() == comparingNumber.toDouble()
        }

        @JvmStatic fun notEqualAndNotNull(initialNumber: Number?, comparingNumber: Number?): Boolean {
            return initialNumber != null && comparingNumber != null
                    && initialNumber.toDouble() != comparingNumber.toDouble()
        }

        @JvmStatic fun notEqual(initialNumber: Number?, comparingNumber: Number?): Boolean {
            var initial = 0.0
            if (initialNumber != null) {
                initial = initialNumber.toDouble()
            }
            var comparing = 0.0
            if (comparingNumber != null) {
                comparing = comparingNumber.toDouble()
            }
            return initial != comparing
        }

        @JvmStatic fun greaterThanZero(number: Number?): Boolean {
            return number != null && number.toDouble() > 0.0
        }

        @JvmStatic fun equalOrGreaterThanZero(number: Number?): Boolean {
            return number != null && number.toDouble() >= 0.0
        }

        @JvmStatic fun greaterThan(initialNumber: Number?, comparingNumber: Number): Boolean {
            return initialNumber != null && initialNumber.toDouble() > comparingNumber.toDouble()
        }

        @JvmStatic fun equalOrGreaterThan(initialNumber: Number?, comparingNumber: Number): Boolean {
            return initialNumber != null && initialNumber.toDouble() >= comparingNumber.toDouble()
        }


        @JvmStatic fun lessThanZero(number: Number?): Boolean {
            return number != null && number.toDouble() < 0.0
        }

        @JvmStatic fun equalOrLessThanZero(number: Number?): Boolean {
            return number != null && number.toDouble() <= 0.0
        }

        @JvmStatic fun lessThan(initialNumber: Number?, comparingNumber: Number): Boolean {
            return initialNumber != null && initialNumber.toDouble() < comparingNumber.toDouble()
        }

        @JvmStatic fun nullOrLessThan(initialNumber: Number?, comparingNumber: Number): Boolean {
            return initialNumber == null || initialNumber.toDouble() < comparingNumber.toDouble()
        }

        @JvmStatic fun equalOrLessThan(initialNumber: Number?, comparingNumber: Number): Boolean {
            return initialNumber != null && initialNumber.toDouble() <= comparingNumber.toDouble()
        }

        @JvmStatic fun defaultIfNull(number: Number?): Number {
            return number ?: 0
        }

        @JvmStatic fun defaultIfNull(number: Number?, defaultVal: Number): Number {
            return number ?: defaultVal
        }

        @JvmStatic fun defaultIfNullOrZero(number: Number?, defaultVal: Number): Number {
            return if (number == null || number.toDouble() == 0.0) defaultVal else number
        }

        @JvmStatic fun defaultIfNullEqualOrLessThanZero(number: Number?, defaultVal: Number): Number {
            return if (number == null || number.toDouble() <= 0.0) defaultVal else number
        }
    }
}