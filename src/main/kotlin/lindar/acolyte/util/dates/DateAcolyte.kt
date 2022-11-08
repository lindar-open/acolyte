package lindar.acolyte.util.dates

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.*
import java.util.*

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

        @JvmStatic fun withUTC(): DateAcolyte {
            return DateAcolyte(ZoneOffset.UTC)
        }

        @JvmStatic fun previousOrSameHour(hourOfDay: Int): TemporalAdjuster {
            return TemporalAdjuster { temporal: Temporal ->
                val calHod = temporal[ChronoField.HOUR_OF_DAY]
                if (calHod == hourOfDay) {
                    return@TemporalAdjuster temporal
                }
                val hoursDiff = hourOfDay - calHod
                temporal.minus((if (hoursDiff >= 0) 24 - hoursDiff else -hoursDiff).toLong(), ChronoUnit.HOURS)
            }
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

    fun startOfDay(hourOfDay: Int): ZonedDateTime {
        return ZonedDateTime.now(timezone)
            .with(previousOrSameHour(hourOfDay))
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
    }

    fun endOfDay(hourOfDay: Int): ZonedDateTime {
        return ZonedDateTime.now(timezone)
            .plusDays(1)
            .with(previousOrSameHour(hourOfDay))
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
            .minusNanos(1)
    }

    fun startOfMonth(hourOfDay: Int): ZonedDateTime {
        return ZonedDateTime.now(timezone)
            .with(previousOrSameHour(hourOfDay))
            .with(TemporalAdjusters.firstDayOfMonth())
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
    }

    fun endOfMonth(hourOfDay: Int): ZonedDateTime {
        return ZonedDateTime.now(timezone)
            .plusMonths(1)
            .with(previousOrSameHour(hourOfDay))
            .with(TemporalAdjusters.firstDayOfMonth())
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
            .minusNanos(1)
    }

    fun startOfWeek(hourOfDay: Int): ZonedDateTime {
        return ZonedDateTime.now(timezone)
            .with(previousOrSameHour(hourOfDay))
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
    }

    fun endOfWeek(hourOfDay: Int): ZonedDateTime {
        return ZonedDateTime.now(timezone)
            .plusWeeks(1)
            .with(previousOrSameHour(hourOfDay))
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
            .minusNanos(1)
    }
}