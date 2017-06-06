package lindar.acolyte.util

import java.text.DecimalFormat
import java.text.ParseException

class NumberFormatterAcolyte {
    companion object {
        private val DECIMAL_FORMAT = DecimalFormat("#,##0.00")
        private val DECIMAL_FORMAT_NO_EXTRA_DIGITS = DecimalFormat("#,##0.######")

        private val DECIMAL_FORMAT_NO_COMMA = DecimalFormat("###0.00")
        private val DECIMAL_FORMAT_NO_EXTRA_DIGITS_NO_COMMA = DecimalFormat("###0.##")

        private val POUND_SIGN = "£"
        private val PENNY_SIGN = "p"

        @JvmStatic
        fun formatNumberWithoutComma(number: Number?): String {
            if (number == null) {
                return 0.toString()
            }
            if (number.toDouble() % 1 == 0.0) {
                return DECIMAL_FORMAT_NO_EXTRA_DIGITS_NO_COMMA.format(number)
            }
            return remove0Decimals(DECIMAL_FORMAT_NO_COMMA.format(number))
        }

        @JvmStatic
        fun formatNumber(number: Number?): String {
            if (number == null) {
                return 0.toString()
            }
            if (number.toDouble() % 1 == 0.0) {
                return DECIMAL_FORMAT_NO_EXTRA_DIGITS.format(number)
            }
            return remove0Decimals(DECIMAL_FORMAT.format(number))
        }

        @JvmStatic
        fun formatNumber(prefix: String, number: Number): String {
            return prefix + formatNumber(number)
        }

        @JvmStatic
        fun formatNumber(number: Number, suffix: String): String {
            return formatNumber(number) + suffix
        }

        private fun remove0Decimals(number: String): String {
            if (number.matches(""".\.0+""".toRegex())) {
                return number.substring(0, number.lastIndexOf('.'))
            }
            return number
        }

        @JvmStatic
        fun formatMoney(amount: Number): String {
            val amountDoubleVal = amount.toDouble()
            if (amountDoubleVal == 0.0) {
                return "0"
            }
            if (amountDoubleVal >= 1) {
                return POUND_SIGN + formatNumber(amount)
            }
            return PENNY_SIGN + DECIMAL_FORMAT.format(amountDoubleVal * 100)
        }

        @JvmStatic
        fun formatNumber(amount: String): String {
            if (amount.toDoubleOrNull() == null) {
                return 0.toString()
            }
            var amountDoubleVal: Double?
            try {
                amountDoubleVal = DECIMAL_FORMAT.parse(amount).toDouble()
            } catch (pe: ParseException) {
                amountDoubleVal = 0.toDouble()
            }

            return formatNumber(amountDoubleVal)
        }

        @JvmStatic
        fun formatMoney(amount: String): String {
            return POUND_SIGN + formatNumber(amount)
        }

        @JvmStatic
        fun addAndFormatMoney(firstAmount: String, secondAmount: String): String {
            return POUND_SIGN + addAndFormatNumber(firstAmount, secondAmount)
        }

        @JvmStatic
        fun addAndFormatNumber(firstAmount: String, secondAmount: String): String {
            var firstAmountDoubleVal: Double
            var secondAmountDoubleVal: Double

            if (firstAmount.toDoubleOrNull() == null) {
                try {
                    firstAmountDoubleVal = DECIMAL_FORMAT.parse(firstAmount).toDouble()
                } catch (pe: ParseException) {
                    firstAmountDoubleVal = 0.toDouble()
                }

            } else {
                firstAmountDoubleVal = 0.toDouble()
            }

            if (secondAmount.toDoubleOrNull() == null) {
                try {
                    secondAmountDoubleVal = DECIMAL_FORMAT.parse(secondAmount).toDouble()
                } catch (pe: ParseException) {
                    secondAmountDoubleVal = 0.toDouble()
                }

            } else {
                secondAmountDoubleVal = 0.toDouble()
            }

            return formatNumber(firstAmountDoubleVal + secondAmountDoubleVal)
        }
    }
}
