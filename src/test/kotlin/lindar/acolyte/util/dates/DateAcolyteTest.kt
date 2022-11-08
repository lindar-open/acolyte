package lindar.acolyte.util.dates

import lindar.acolyte.util.dates.DateAcolyte.Companion.withTimezone
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mockStatic
import java.time.*

internal class DateAcolyteTest {

    @Test
    fun startOfDay() {
        withTimezone(ZoneOffset.UTC).startOfDay()
        val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-11-08T00:00:00Z"), DateAcolyte.withUTC().startOfDay(0))
            assertEquals(ZonedDateTime.parse("2022-11-08T05:00:00Z"), DateAcolyte.withUTC().startOfDay(5))
            assertEquals(ZonedDateTime.parse("2022-11-08T09:00:00Z"), DateAcolyte.withUTC().startOfDay(9))
            assertEquals(ZonedDateTime.parse("2022-11-07T10:00:00Z"), DateAcolyte.withUTC().startOfDay(10))
            assertEquals(ZonedDateTime.parse("2022-11-07T22:00:00Z"), DateAcolyte.withUTC().startOfDay(22))
        }
    }

    @Test
    fun endOfDay() {
        val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-11-08T23:59:59.999999999Z"), DateAcolyte.withUTC().endOfDay(0))
            assertEquals(ZonedDateTime.parse("2022-11-09T04:59:59.999999999Z"), DateAcolyte.withUTC().endOfDay(5))
            assertEquals(ZonedDateTime.parse("2022-11-09T08:59:59.999999999Z"), DateAcolyte.withUTC().endOfDay(9))
            assertEquals(ZonedDateTime.parse("2022-11-08T09:59:59.999999999Z"), DateAcolyte.withUTC().endOfDay(10))
            assertEquals(ZonedDateTime.parse("2022-11-08T21:59:59.999999999Z"), DateAcolyte.withUTC().endOfDay(22))
        }
    }

    @Test
    fun startOfMonth() {
        val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-11-01T00:00:00Z"), DateAcolyte.withUTC().startOfMonth(0))
            assertEquals(ZonedDateTime.parse("2022-11-01T05:00:00Z"), DateAcolyte.withUTC().startOfMonth(5))
            assertEquals(ZonedDateTime.parse("2022-11-01T09:00:00Z"), DateAcolyte.withUTC().startOfMonth(9))
            assertEquals(ZonedDateTime.parse("2022-11-01T10:00:00Z"), DateAcolyte.withUTC().startOfMonth(10))
            assertEquals(ZonedDateTime.parse("2022-11-01T22:00:00Z"), DateAcolyte.withUTC().startOfMonth(22))
        }
        val date2 = ZonedDateTime.of(LocalDate.of(2022, 11, 1), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date2.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-11-01T00:00:00Z"), DateAcolyte.withUTC().startOfMonth(0))
            assertEquals(ZonedDateTime.parse("2022-11-01T05:00:00Z"), DateAcolyte.withUTC().startOfMonth(5))
            assertEquals(ZonedDateTime.parse("2022-11-01T09:00:00Z"), DateAcolyte.withUTC().startOfMonth(9))
            assertEquals(ZonedDateTime.parse("2022-10-01T10:00:00Z"), DateAcolyte.withUTC().startOfMonth(10))
            assertEquals(ZonedDateTime.parse("2022-10-01T22:00:00Z"), DateAcolyte.withUTC().startOfMonth(22))
        }
        val date3 = ZonedDateTime.of(LocalDate.of(2022, 10, 1), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date3.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-10-01T00:00:00Z"), DateAcolyte.withUTC().startOfMonth(0))
            assertEquals(ZonedDateTime.parse("2022-10-01T05:00:00Z"), DateAcolyte.withUTC().startOfMonth(5))
            assertEquals(ZonedDateTime.parse("2022-10-01T09:00:00Z"), DateAcolyte.withUTC().startOfMonth(9))
            assertEquals(ZonedDateTime.parse("2022-09-01T10:00:00Z"), DateAcolyte.withUTC().startOfMonth(10))
            assertEquals(ZonedDateTime.parse("2022-09-01T22:00:00Z"), DateAcolyte.withUTC().startOfMonth(22))
        }
    }

    @Test
    fun endOfMonth() {
        val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-11-30T23:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(0))
            assertEquals(ZonedDateTime.parse("2022-12-01T04:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(5))
            assertEquals(ZonedDateTime.parse("2022-12-01T08:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(9))
            assertEquals(ZonedDateTime.parse("2022-12-01T09:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(10))
            assertEquals(ZonedDateTime.parse("2022-12-01T21:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(22))
        }
        val date2 = ZonedDateTime.of(LocalDate.of(2022, 11, 30), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date2.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-11-30T23:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(0))
            assertEquals(ZonedDateTime.parse("2022-12-01T04:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(5))
            assertEquals(ZonedDateTime.parse("2022-12-01T08:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(9))
            assertEquals(ZonedDateTime.parse("2022-12-01T09:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(10))
            assertEquals(ZonedDateTime.parse("2022-12-01T21:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(22))
        }
        val date3 = ZonedDateTime.of(LocalDate.of(2022, 12, 1), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date3.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-12-31T23:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(0))
            assertEquals(ZonedDateTime.parse("2023-01-01T04:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(5))
            assertEquals(ZonedDateTime.parse("2023-01-01T08:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(9))
            assertEquals(ZonedDateTime.parse("2022-12-01T09:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(10))
            assertEquals(ZonedDateTime.parse("2022-12-01T21:59:59.999999999Z"), DateAcolyte.withUTC().endOfMonth(22))
        }
    }

    @Test
    fun startOfWeek() {
        val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-11-07T00:00:00Z"), DateAcolyte.withUTC().startOfWeek(0))
            assertEquals(ZonedDateTime.parse("2022-11-07T05:00:00Z"), DateAcolyte.withUTC().startOfWeek(5))
            assertEquals(ZonedDateTime.parse("2022-11-07T09:00:00Z"), DateAcolyte.withUTC().startOfWeek(9))
            assertEquals(ZonedDateTime.parse("2022-11-07T10:00:00Z"), DateAcolyte.withUTC().startOfWeek(10))
            assertEquals(ZonedDateTime.parse("2022-11-07T22:00:00Z"), DateAcolyte.withUTC().startOfWeek(22))
        }
        val date2 = ZonedDateTime.of(LocalDate.of(2022, 11, 7), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date2.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-11-07T00:00:00Z"), DateAcolyte.withUTC().startOfWeek(0))
            assertEquals(ZonedDateTime.parse("2022-11-07T05:00:00Z"), DateAcolyte.withUTC().startOfWeek(5))
            assertEquals(ZonedDateTime.parse("2022-11-07T09:00:00Z"), DateAcolyte.withUTC().startOfWeek(9))
            assertEquals(ZonedDateTime.parse("2022-10-31T10:00:00Z"), DateAcolyte.withUTC().startOfWeek(10))
            assertEquals(ZonedDateTime.parse("2022-10-31T22:00:00Z"), DateAcolyte.withUTC().startOfWeek(22))
        }
        val date3 = ZonedDateTime.of(LocalDate.of(2022, 10, 31), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date3.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-10-31T00:00:00Z"), DateAcolyte.withUTC().startOfWeek(0))
            assertEquals(ZonedDateTime.parse("2022-10-31T05:00:00Z"), DateAcolyte.withUTC().startOfWeek(5))
            assertEquals(ZonedDateTime.parse("2022-10-31T09:00:00Z"), DateAcolyte.withUTC().startOfWeek(9))
            assertEquals(ZonedDateTime.parse("2022-10-24T10:00:00Z"), DateAcolyte.withUTC().startOfWeek(10))
            assertEquals(ZonedDateTime.parse("2022-10-24T22:00:00Z"), DateAcolyte.withUTC().startOfWeek(22))
        }
    }

    @Test
    fun endOfWeek() {
        val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-11-13T23:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(0))
            assertEquals(ZonedDateTime.parse("2022-11-14T04:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(5))
            assertEquals(ZonedDateTime.parse("2022-11-14T08:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(9))
            assertEquals(ZonedDateTime.parse("2022-11-14T09:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(10))
            assertEquals(ZonedDateTime.parse("2022-11-14T21:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(22))
        }
        val date2 = ZonedDateTime.of(LocalDate.of(2022, 11, 7), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date2.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-11-13T23:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(0))
            assertEquals(ZonedDateTime.parse("2022-11-14T04:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(5))
            assertEquals(ZonedDateTime.parse("2022-11-14T08:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(9))
            assertEquals(ZonedDateTime.parse("2022-11-07T09:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(10))
            assertEquals(ZonedDateTime.parse("2022-11-07T21:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(22))
        }
        val date3 = ZonedDateTime.of(LocalDate.of(2022, 11, 14), LocalTime.of(9, 55), ZoneOffset.UTC)
        mock(date3.toInstant()) {
            assertEquals(ZonedDateTime.parse("2022-11-20T23:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(0))
            assertEquals(ZonedDateTime.parse("2022-11-21T04:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(5))
            assertEquals(ZonedDateTime.parse("2022-11-21T08:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(9))
            assertEquals(ZonedDateTime.parse("2022-11-14T09:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(10))
            assertEquals(ZonedDateTime.parse("2022-11-14T21:59:59.999999999Z"), DateAcolyte.withUTC().endOfWeek(22))
        }
    }

    @Test
    fun previousOrSameHour() {
        assertEquals(
            ZonedDateTime.parse("2022-11-13T14:55:00Z"),
            ZonedDateTime.of(LocalDate.of(2022, 11, 14), LocalTime.of(9, 55), ZoneOffset.UTC)
                .with(DateAcolyte.previousOrSameHour(14))
        )
        assertEquals(
            ZonedDateTime.parse("2022-11-14T14:55:00Z"),
            ZonedDateTime.of(LocalDate.of(2022, 11, 14), LocalTime.of(14, 55), ZoneOffset.UTC)
                .with(DateAcolyte.previousOrSameHour(14))
        )
        assertEquals(
            ZonedDateTime.parse("2022-11-14T14:55:00Z"),
            ZonedDateTime.of(LocalDate.of(2022, 11, 14), LocalTime.of(15, 55), ZoneOffset.UTC)
                .with(DateAcolyte.previousOrSameHour(14))
        )
        assertEquals(
            ZonedDateTime.parse("2022-11-13T14:55:00Z"),
            ZonedDateTime.of(LocalDate.of(2022, 11, 14), LocalTime.of(0, 55), ZoneOffset.UTC)
                .with(DateAcolyte.previousOrSameHour(14))
        )
        assertEquals(
            ZonedDateTime.parse("2022-10-31T14:55:00Z"),
            ZonedDateTime.of(LocalDate.of(2022, 11, 1), LocalTime.of(0, 55), ZoneOffset.UTC)
                .with(DateAcolyte.previousOrSameHour(14))
        )
    }

    private fun mock(current: Instant, test: Runnable) {
        val fixedClock = Clock.fixed(current, ZoneOffset.UTC)
        mockStatic(Clock::class.java).use { instantMock ->
            instantMock.`when`<Clock> { Clock.systemDefaultZone() }.thenReturn(fixedClock)
            instantMock.`when`<Clock> { Clock.system(any()) }.thenReturn(fixedClock)
            instantMock.`when`<Clock> { Clock.systemUTC() }.thenReturn(fixedClock)
            test.run()
        }
    }
}