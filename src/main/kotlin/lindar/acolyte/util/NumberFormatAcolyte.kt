package lindar.acolyte.util

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class NumberFormatAcolyte {
    companion object {
        @JvmStatic fun builder(): NumberFormatAcolyte {
            return NumberFormatAcolyte()
        }
    }

    private var locale = Locale.UK
    private var showTrailingZero = false
    private var showThousandsSeparator = true
    private var showCurrency = false
    private var showFractionUnitBelowOne = false
    private var fractionUnit = "p"
    private var minFractionDigits = 2
    private var maxFractionDigits = 2
    private var roundingMode = RoundingMode.HALF_UP
    private var prefix = ""
    private var suffix = ""
    private var pattern: String? = null

    fun locale(currentLocale: Locale): NumberFormatAcolyte {
        locale = currentLocale
        return this
    }

    fun showTrailingZeros(): NumberFormatAcolyte {
        showTrailingZero = true
        return this
    }

    fun hideTrailingZeros(): NumberFormatAcolyte {
        showTrailingZero = false
        return this
    }

    fun showThousandsSeparator(): NumberFormatAcolyte {
        showThousandsSeparator = true
        return this
    }

    fun hideThousandsSeparator(): NumberFormatAcolyte {
        showThousandsSeparator = false
        return this
    }

    fun showCurrency(): NumberFormatAcolyte {
        showCurrency = true
        return this
    }

    fun hideCurrency(): NumberFormatAcolyte {
        showCurrency = false
        return this
    }

    @Deprecated(
        "This function is deprecated. Use showFractionUnitBelowOne() instead.",
        ReplaceWith("showFractionUnitBelowOne()")
    )
    fun showPenniesBelowOne(): NumberFormatAcolyte {
        return showFractionUnitBelowOne()
    }

    @Deprecated(
        "This function is deprecated. Use hideFractionUnitBelowOne() instead.",
        ReplaceWith("hideFractionUnitBelowOne()")
    )
    fun hidePenniesBelowOne(): NumberFormatAcolyte {
        return hideFractionUnitBelowOne()
    }

    fun showFractionUnitBelowOne(): NumberFormatAcolyte {
        showFractionUnitBelowOne = true
        return this
    }

    fun hideFractionUnitBelowOne(): NumberFormatAcolyte {
        showFractionUnitBelowOne = false
        return this
    }

    fun fractionUnit(currentFractionUnit: String): NumberFormatAcolyte {
        fractionUnit = currentFractionUnit
        return this
    }

    fun wholeNumber(): NumberFormatAcolyte {
        minFractionDigits = 0
        maxFractionDigits = 0
        return this
    }

    fun fractionDigits(digits: Int): NumberFormatAcolyte {
        minFractionDigits = digits
        maxFractionDigits = digits
        return this
    }

    fun minFractionDigits(digits: Int): NumberFormatAcolyte {
        minFractionDigits = digits
        return this
    }

    fun maxFractionDigits(digits: Int): NumberFormatAcolyte {
        maxFractionDigits = digits
        return this
    }

    fun roundingMode(currentRoundingMode: RoundingMode): NumberFormatAcolyte {
        roundingMode = currentRoundingMode
        return this
    }

    fun roundUp(): NumberFormatAcolyte {
        roundingMode = RoundingMode.UP
        return this
    }

    fun roundHalfUp(): NumberFormatAcolyte {
        roundingMode = RoundingMode.HALF_UP
        return this
    }

    fun roundDown(): NumberFormatAcolyte {
        roundingMode = RoundingMode.DOWN
        return this
    }

    fun roundHalfDown(): NumberFormatAcolyte {
        roundingMode = RoundingMode.HALF_DOWN
        return this
    }

    fun prefix(currentPrefix: String): NumberFormatAcolyte {
        prefix = currentPrefix
        return this
    }

    fun suffix(currentSuffix: String): NumberFormatAcolyte {
        suffix = currentSuffix
        return this
    }

    fun pattern(currentPattern: String): NumberFormatAcolyte {
        pattern = currentPattern
        return this
    }

    fun formatToNull(number: Number?): String? {
        return number?.let { format(it) }
    }

    fun formatOrDefault(number: Number?, defaultValue: String): String {
        return if (number != null) format(number) else defaultValue
    }

    fun format(number: Number?): String {
        val amountDoubleVal = number?.toDouble() ?: return 0.toString()

        val shouldShowFractionUnit = showFractionUnitBelowOne && NumbersAcolyte.lessThanOne(number)
        val decimalFormat = if (showCurrency && !shouldShowFractionUnit) {
            NumberFormat.getCurrencyInstance(locale) as DecimalFormat
        } else {
            NumberFormat.getNumberInstance(locale) as DecimalFormat
        }
        if (!pattern.isNullOrBlank()) return decimalFormat.let {
            it.applyPattern(pattern)
            it.format(number)
        }

        decimalFormat.isGroupingUsed = showThousandsSeparator
        decimalFormat.maximumFractionDigits = maxFractionDigits
        decimalFormat.minimumFractionDigits = minFractionDigits
        decimalFormat.roundingMode = roundingMode

        if (shouldShowFractionUnit) {
            return prefix + remove0Decimals(decimalFormat.format(amountDoubleVal * 100)) + fractionUnit + suffix
        }

        val formattedNumber = decimalFormat.format(number)
        if (!showTrailingZero && number.toDouble() % 1 == 0.0) {
            return prefix + remove0Decimals(formattedNumber) + suffix
        }
        return prefix + formattedNumber + suffix
    }

    private fun remove0Decimals(number: String?): String {
        if (number != null && number.matches(""".*\.0+""".toRegex())) {
            return number.substring(0, number.lastIndexOf('.'))
        }
        return number.orEmpty()
    }
}
