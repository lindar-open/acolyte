package lindar.acolyte.util

import java.math.BigDecimal

class BigDecimalAcolyte {
    companion object {

        @JvmStatic
        fun defaultIfNull(number: BigDecimal?): BigDecimal {
            return number ?: BigDecimal.ZERO
        }

        @JvmStatic
        fun defaultIfNull(number: BigDecimal?, defaultVal: BigDecimal): BigDecimal {
            return number ?: defaultVal
        }

        @JvmStatic
        fun defaultIfNullOrZero(number: BigDecimal?, defaultVal: BigDecimal): BigDecimal {
            return if (number == null || number == BigDecimal.ZERO) defaultVal else number
        }

        @JvmStatic
        fun defaultIfNullEqualOrLessThanZero(number: BigDecimal?, defaultVal: BigDecimal): BigDecimal {
            return if (number == null || number <= BigDecimal.ZERO) defaultVal else number
        }
    }
}