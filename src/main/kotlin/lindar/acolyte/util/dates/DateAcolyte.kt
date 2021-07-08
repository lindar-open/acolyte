package lindar.acolyte.util.dates

import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Date
import java.util.Locale

class DateAcolyte(private var timezone: ZoneId) {
    companion object {
        @JvmStatic fun withSystemDefaultTimezone(): DateAcolyte {
            return DateAcolyte(ZoneId.systemDefault())
        }

        @JvmStatic fun withLondonTimezone(): DateAcolyte {
            return DateAcolyte(ZoneId.of("Europe/London"))
        }

        @JvmStatic fun withTimezone(zoneId: ZoneId): DateAcolyte {
            return DateAcolyte(zoneId)
        }
    }
    private var formatter: DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    fun andIsoOffsetDateTimeFormatter(): DateAcolyte {
        return this.apply { formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME }
    }

    fun andFormatter(dateTimeFormatter: DateTimeFormatter): DateAcolyte {
        return this.apply { formatter = dateTimeFormatter }
    }

    fun andPattern(pattern: String): DateAcolyte {
        return this.apply { formatter = DateTimeFormatter.ofPattern(pattern) }
    }

    fun andPatternAndLocale(pattern: String, locale: Locale): DateAcolyte {
        return this.apply { formatter = DateTimeFormatter.ofPattern(pattern, locale) }
    }

    fun format(date: Date): String {
        return ZonedDateTime.ofInstant(date.toInstant(), timezone).format(formatter)
    }

    fun format(instant: Instant): String {
        return ZonedDateTime.ofInstant(instant, timezone).format(formatter)
    }

    fun format(zonedDateTime: ZonedDateTime): String {
        return zonedDateTime.format(formatter)
    }

    fun parser(): DateParserAcolyte {
        return DateParserAcolyte(formatter)
    }


    fun toLocalDateTime(instant: Instant): LocalDateTime {
        return LocalDateTime.ofInstant(instant, timezone)
    }

    fun startOfDay(): ZonedDateTime {
        return ZonedDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0), timezone)
    }

    fun endOfDay(): ZonedDateTime {
        return ZonedDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59), timezone)
    }

    fun startOfWeek(): ZonedDateTime {
        return ZonedDateTime.now(timezone).with(DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0)
    }

   fun endOfWeek(): ZonedDateTime {
        return ZonedDateTime.now(timezone).with(DayOfWeek.SUNDAY).withHour(23).withMinute(59).withSecond(59)
    }

    fun startOfMonth(): ZonedDateTime {
        return ZonedDateTime.now(timezone).with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0)
    }

    fun endOfMonth(): ZonedDateTime {
        return ZonedDateTime.now(timezone).with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59)
    }

    fun startOfYear(): ZonedDateTime {
        return ZonedDateTime.now(timezone).with(TemporalAdjusters.firstDayOfYear()).withHour(0).withMinute(0).withSecond(0)
    }

    fun endOfYear(): ZonedDateTime {
        return ZonedDateTime.now(timezone).with(TemporalAdjusters.lastDayOfYear()).withHour(23).withMinute(59).withSecond(59)
    }

}