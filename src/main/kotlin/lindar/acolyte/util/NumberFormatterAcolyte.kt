package lindar.acolyte.util

import java.text.DecimalFormat
import java.text.ParseException

class NumberFormatterAcolyte {
    companion object {
        private val DECIMAL_FORMAT = DecimalFormat("#,##0.00####")
        private val DECIMAL_FORMAT_NO_EXTRA_DIGITS = DecimalFormat("#,##0.######")

        private val DECIMAL_FORMAT_NO_COMMA = DecimalFormat("###0.00")
        private val DECIMAL_FORMAT_NO_EXTRA_DIGITS_NO_COMMA = DecimalFormat("###0.##")

        private const val POUND_SIGN = "£"
        private const val PENNY_SIGN = "p"

        @JvmStatic fun formatNumberWithoutComma(number: Number?): String {
            if (number == null) {
                return 0.toString()
            }
            if (number.toDouble() % 1 == 0.0) {
                return DECIMAL_FORMAT_NO_EXTRA_DIGITS_NO_COMMA.format(number)
            }
            return remove0Decimals(DECIMAL_FORMAT_NO_COMMA.format(number))
        }

        @JvmStatic fun formatNumber(prefix: String?, number: Number?): String {
            return prefix.orEmpty() + formatNumber(number, DECIMAL_FORMAT)
        }

        @JvmStatic fun formatNumber(number: Number?, suffix: String?): String {
            return formatNumber(number, DECIMAL_FORMAT) + suffix
        }

        @JvmStatic fun formatNumber(number: Number?): String {
            return formatNumber(number, DECIMAL_FORMAT)
        }

        @JvmStatic fun formatNumber(number: Number?, decimalFormat: DecimalFormat): String {
            if (number == null) {
                return 0.toString()
            }
            if (number.toDouble() % 1 == 0.0) {
                return DECIMAL_FORMAT_NO_EXTRA_DIGITS.format(number)
            }
            return remove0Decimals(decimalFormat.format(number))
        }

        @JvmStatic fun formatNumber(amount: String?): String {
            return formatNumber(amount, DECIMAL_FORMAT)
        }

        @JvmStatic fun formatNumber(amount: String?, decimalFormat: DecimalFormat): String {
            if (amount?.toDoubleOrNull() == null) {
                return 0.toString()
            }
            var amountDoubleVal: Double?
            try {
                amountDoubleVal = decimalFormat.parse(amount).toDouble()
            } catch (pe: ParseException) {
                amountDoubleVal = 0.toDouble()
            }
            return formatNumber(amountDoubleVal, decimalFormat)
        }


        @JvmStatic fun formatMoney(amount: String?): String {
            return formatMoney(amount, DECIMAL_FORMAT)
        }

        @JvmStatic fun formatMoney(amount: String?, decimalFormat: DecimalFormat): String {
            return POUND_SIGN + formatNumber(amount, decimalFormat)
        }

        @JvmStatic fun formatMoney(amount: Number?): String {
            return formatMoney(amount, DECIMAL_FORMAT)
        }

        @JvmStatic fun formatMoney(amount: Number?, decimalFormat: DecimalFormat): String {
            val amountDoubleVal = amount?.toDouble()
            if (amountDoubleVal == null || amountDoubleVal == 0.0) {
                return 0.toString()
            }
            if (amountDoubleVal >= 1) {
                return POUND_SIGN + formatNumber(amount, decimalFormat)
            }
            return PENNY_SIGN + DECIMAL_FORMAT.format(amountDoubleVal * 100)
        }

        @JvmStatic fun addAndFormatMoney(firstAmount: String?, secondAmount: String?): String {
            return POUND_SIGN + addAndFormatNumber(firstAmount, secondAmount)
        }

        @JvmStatic fun addAndFormatNumber(firstAmount: String?, secondAmount: String?): String {
            var firstAmountDoubleVal: Double
            var secondAmountDoubleVal: Double

            if (firstAmount != null && firstAmount.toDoubleOrNull() != null) {
                try {
                    firstAmountDoubleVal = DECIMAL_FORMAT.parse(firstAmount).toDouble()
                } catch (pe: ParseException) {
                    firstAmountDoubleVal = 0.toDouble()
                }

            } else {
                firstAmountDoubleVal = 0.toDouble()
            }

            if (secondAmount != null && secondAmount.toDoubleOrNull() != null) {
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

        private fun remove0Decimals(number: String?): String {
            if (number != null && number.matches(""".\.0+""".toRegex())) {
                return number.substring(0, number.lastIndexOf('.'))
            }
            return number.orEmpty()
        }
    }
}




