package lindar.acolyte.util.dates

import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class DateParserAcolyte(private var formatter: DateTimeFormatter) {

    companion object {
        @JvmStatic fun withIsoOffsetDateTimeFormatter(): DateParserAcolyte {
            return DateParserAcolyte(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        }

        @JvmStatic fun withFormatter(dateTimeFormatter: DateTimeFormatter): DateParserAcolyte {
            return DateParserAcolyte(dateTimeFormatter)
        }

        @JvmStatic fun withPattern(pattern: String): DateParserAcolyte {
            return DateParserAcolyte(DateTimeFormatter.ofPattern(pattern))
        }

        @JvmStatic fun withPatternAndLocale(pattern: String, locale: Locale): DateParserAcolyte {
            return DateParserAcolyte(DateTimeFormatter.ofPattern(pattern, locale))
        }
    }

    fun parse(dateString: String): ZonedDateTime {
        return ZonedDateTime.parse(dateString, formatter)
    }

    fun parseUTC(dateString: String): Instant {
        return ZonedDateTime.parse(dateString, formatter).toInstant()
    }
}