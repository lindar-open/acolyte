package lindar.acolyte.vo

import java.time.temporal.ChronoUnit

data class ApproximateDuration(val duration: Int, val unit: ChronoUnit) {

    fun getDurationFormatted(): String {
        return when (unit) {
            ChronoUnit.YEARS -> format(singlePlural(duration, "year", "years"))
            ChronoUnit.MONTHS -> format(singlePlural(duration, "month", "months"))
            ChronoUnit.DAYS -> format(singlePlural(duration, "days", "days"))
            ChronoUnit.HOURS -> format(singlePlural(duration, "hour", "hours"))
            ChronoUnit.MINUTES -> format(singlePlural(duration, "minute", "minutes"))
            else -> format(singlePlural(duration, "second", "seconds"))
        }
    }

    private fun singlePlural(count: Int, singular: String, plural: String): String {
        return if (count == 1) singular else plural
    }

    private fun format(ending: String): String {
        return "$duration $ending"
    }
}