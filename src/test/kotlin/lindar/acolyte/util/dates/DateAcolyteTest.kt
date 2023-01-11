package lindar.acolyte.util.dates

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mockStatic
import java.time.*
import java.time.temporal.ChronoUnit
import java.util.stream.Stream

internal class DateAcolyteTest {

    @ParameterizedTest
    @MethodSource("startOfDayArguments")
    fun startOfDay(expected: String, hourOfDay: Int, currentDate: ZonedDateTime, zoneId: ZoneId) {
        mock(currentDate.toInstant()) {
            assertEquals(ZonedDateTime.parse(expected), DateAcolyte.withTimezone(zoneId).startOfDay(hourOfDay))
        }
    }

    @ParameterizedTest
    @MethodSource("endOfDayArguments")
    fun endOfDay(expected: String, hourOfDay: Int, currentDate: ZonedDateTime, zoneId: ZoneId) {
        mock(currentDate.toInstant()) {
            assertEquals(ZonedDateTime.parse(expected), DateAcolyte.withTimezone(zoneId).endOfDay(hourOfDay))
        }
    }

    @ParameterizedTest
    @MethodSource("startOfWeekArguments")
    fun startOfWeek(expected: String, hourOfDay: Int, currentDate: ZonedDateTime, zoneId: ZoneId) {
        mock(currentDate.toInstant()) {
            assertEquals(ZonedDateTime.parse(expected), DateAcolyte.withTimezone(zoneId).startOfWeek(hourOfDay))
        }
    }

    @ParameterizedTest
    @MethodSource("endOfWeekArguments")
    fun endOfWeek(expected: String, hourOfDay: Int, currentDate: ZonedDateTime, zoneId: ZoneId) {
        mock(currentDate.toInstant()) {
            assertEquals(ZonedDateTime.parse(expected), DateAcolyte.withTimezone(zoneId).endOfWeek(hourOfDay))
        }
    }

    @ParameterizedTest
    @MethodSource("startOfMonthArguments")
    fun startOfMonth(expected: String, hourOfDay: Int, currentDate: ZonedDateTime, zoneId: ZoneId) {
        mock(currentDate.toInstant()) {
            assertEquals(ZonedDateTime.parse(expected), DateAcolyte.withTimezone(zoneId).startOfMonth(hourOfDay))
        }
    }

    @ParameterizedTest
    @MethodSource("endOfMonthArguments")
    fun endOfMonth(expected: String, hourOfDay: Int, currentDate: ZonedDateTime, zoneId: ZoneId) {
        mock(currentDate.toInstant()) {
            assertEquals(ZonedDateTime.parse(expected), DateAcolyte.withTimezone(zoneId).endOfMonth(hourOfDay))
        }
    }

    @ParameterizedTest
    @MethodSource("previousOrSameHourArguments")
    fun previousOrSameHour(expected: String, hourOfDay: Int, currentDate: String) {
        assertEquals(
            ZonedDateTime.parse(expected),
            ZonedDateTime.parse(currentDate)
                .with(DateAcolyte.previousOrSameHour(hourOfDay))
                    )
    }

    companion object {
        @JvmStatic
        private fun startOfDayArguments(): Stream<Arguments> {
            val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
            val dateDuringDST = ZonedDateTime.of(LocalDate.of(2022, 10, 30), LocalTime.of(3, 0), ZoneId.of("Europe/London"))
            return Stream.of(
                Arguments.of("2022-11-08T00:00:00Z", 0, date, ZoneOffset.UTC),
                Arguments.of("2022-11-08T05:00:00Z", 5, date, ZoneOffset.UTC),
                Arguments.of("2022-11-08T09:00:00Z", 9, date, ZoneOffset.UTC),
                Arguments.of("2022-11-07T10:00:00Z", 10, date, ZoneOffset.UTC),
                Arguments.of("2022-11-07T22:00:00Z", 22, date, ZoneOffset.UTC),
                Arguments.of("2022-10-29T22:00:00Z", 22, dateDuringDST, ZoneId.of("Europe/London")),
                            )
        }

        @JvmStatic
        private fun endOfDayArguments(): Stream<Arguments> {
            val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
            return Stream.of(
                Arguments.of("2022-11-08T23:59:59.999999999Z", 0, date, ZoneOffset.UTC),
                Arguments.of("2022-11-09T04:59:59.999999999Z", 5, date, ZoneOffset.UTC),
                Arguments.of("2022-11-09T08:59:59.999999999Z", 9, date, ZoneOffset.UTC),
                Arguments.of("2022-11-08T09:59:59.999999999Z", 10, date, ZoneOffset.UTC),
                Arguments.of("2022-11-08T21:59:59.999999999Z", 22, date, ZoneOffset.UTC),
                            )
        }

        @JvmStatic
        private fun startOfWeekArguments(): Stream<Arguments> {
            val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
            val date2 = ZonedDateTime.of(LocalDate.of(2022, 11, 7), LocalTime.of(9, 55), ZoneOffset.UTC)
            val date3 = ZonedDateTime.of(LocalDate.of(2022, 10, 31), LocalTime.of(9, 55), ZoneOffset.UTC)
            return Stream.of(
                Arguments.of("2022-11-07T00:00:00Z", 0, date, ZoneOffset.UTC),
                Arguments.of("2022-11-07T05:00:00Z", 5, date, ZoneOffset.UTC),
                Arguments.of("2022-11-07T09:00:00Z", 9, date, ZoneOffset.UTC),
                Arguments.of("2022-11-07T10:00:00Z", 10, date, ZoneOffset.UTC),
                Arguments.of("2022-11-07T22:00:00Z", 22, date, ZoneOffset.UTC),
                Arguments.of("2022-11-07T00:00:00Z", 0, date2, ZoneOffset.UTC),
                Arguments.of("2022-11-07T05:00:00Z", 5, date2, ZoneOffset.UTC),
                Arguments.of("2022-11-07T09:00:00Z", 9, date2, ZoneOffset.UTC),
                Arguments.of("2022-10-31T10:00:00Z", 10, date2, ZoneOffset.UTC),
                Arguments.of("2022-10-31T22:00:00Z", 22, date2, ZoneOffset.UTC),
                Arguments.of("2022-10-31T00:00:00Z", 0, date3, ZoneOffset.UTC),
                Arguments.of("2022-10-31T05:00:00Z", 5, date3, ZoneOffset.UTC),
                Arguments.of("2022-10-31T09:00:00Z", 9, date3, ZoneOffset.UTC),
                Arguments.of("2022-10-24T10:00:00Z", 10, date3, ZoneOffset.UTC),
                Arguments.of("2022-10-24T22:00:00Z", 22, date3, ZoneOffset.UTC)
                            )
        }

        @JvmStatic
        private fun endOfWeekArguments(): Stream<Arguments> {
            val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
            val date2 = ZonedDateTime.of(LocalDate.of(2022, 11, 7), LocalTime.of(9, 55), ZoneOffset.UTC)
            val date3 = ZonedDateTime.of(LocalDate.of(2022, 11, 14), LocalTime.of(9, 55), ZoneOffset.UTC)
            return Stream.of(
                Arguments.of("2022-11-13T23:59:59.999999999Z", 0, date, ZoneOffset.UTC),
                Arguments.of("2022-11-14T04:59:59.999999999Z", 5, date, ZoneOffset.UTC),
                Arguments.of("2022-11-14T08:59:59.999999999Z", 9, date, ZoneOffset.UTC),
                Arguments.of("2022-11-14T09:59:59.999999999Z", 10, date, ZoneOffset.UTC),
                Arguments.of("2022-11-14T21:59:59.999999999Z", 22, date, ZoneOffset.UTC),
                Arguments.of("2022-11-13T23:59:59.999999999Z", 0, date2, ZoneOffset.UTC),
                Arguments.of("2022-11-14T04:59:59.999999999Z", 5, date2, ZoneOffset.UTC),
                Arguments.of("2022-11-14T08:59:59.999999999Z", 9, date2, ZoneOffset.UTC),
                Arguments.of("2022-11-07T09:59:59.999999999Z", 10, date2, ZoneOffset.UTC),
                Arguments.of("2022-11-07T21:59:59.999999999Z", 22, date2, ZoneOffset.UTC),
                Arguments.of("2022-11-20T23:59:59.999999999Z", 0, date3, ZoneOffset.UTC),
                Arguments.of("2022-11-21T04:59:59.999999999Z", 5, date3, ZoneOffset.UTC),
                Arguments.of("2022-11-21T08:59:59.999999999Z", 9, date3, ZoneOffset.UTC),
                Arguments.of("2022-11-14T09:59:59.999999999Z", 10, date3, ZoneOffset.UTC),
                Arguments.of("2022-11-14T21:59:59.999999999Z", 22, date3, ZoneOffset.UTC),
                            )
        }

        @JvmStatic
        private fun startOfMonthArguments(): Stream<Arguments> {
            val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
            val date2 = ZonedDateTime.of(LocalDate.of(2022, 11, 1), LocalTime.of(9, 55), ZoneOffset.UTC)
            val date3 = ZonedDateTime.of(LocalDate.of(2022, 10, 1), LocalTime.of(9, 55), ZoneOffset.UTC)
            return Stream.of(
                Arguments.of("2022-11-01T00:00:00Z", 0, date, ZoneOffset.UTC),
                Arguments.of("2022-11-01T05:00:00Z", 5, date, ZoneOffset.UTC),
                Arguments.of("2022-11-01T09:00:00Z", 9, date, ZoneOffset.UTC),
                Arguments.of("2022-11-01T10:00:00Z", 10, date, ZoneOffset.UTC),
                Arguments.of("2022-11-01T22:00:00Z", 22, date, ZoneOffset.UTC),
                Arguments.of("2022-11-01T00:00:00Z", 0, date2, ZoneOffset.UTC),
                Arguments.of("2022-11-01T05:00:00Z", 5, date2, ZoneOffset.UTC),
                Arguments.of("2022-11-01T09:00:00Z", 9, date2, ZoneOffset.UTC),
                Arguments.of("2022-10-01T10:00:00Z", 10, date2, ZoneOffset.UTC),
                Arguments.of("2022-10-01T22:00:00Z", 22, date2, ZoneOffset.UTC),
                Arguments.of("2022-10-01T00:00:00Z", 0, date3, ZoneOffset.UTC),
                Arguments.of("2022-10-01T05:00:00Z", 5, date3, ZoneOffset.UTC),
                Arguments.of("2022-10-01T09:00:00Z", 9, date3, ZoneOffset.UTC),
                Arguments.of("2022-09-01T10:00:00Z", 10, date3, ZoneOffset.UTC),
                Arguments.of("2022-09-01T22:00:00Z", 22, date3, ZoneOffset.UTC)
                            )
        }

        @JvmStatic
        private fun endOfMonthArguments(): Stream<Arguments> {
            val date = ZonedDateTime.of(LocalDate.of(2022, 11, 8), LocalTime.of(9, 55), ZoneOffset.UTC)
            val date2 = ZonedDateTime.of(LocalDate.of(2022, 11, 30), LocalTime.of(9, 55), ZoneOffset.UTC)
            val date3 = ZonedDateTime.of(LocalDate.of(2022, 12, 1), LocalTime.of(9, 55), ZoneOffset.UTC)
            return Stream.of(
                Arguments.of("2022-11-30T23:59:59.999999999Z", 0, date, ZoneOffset.UTC),
                Arguments.of("2022-12-01T04:59:59.999999999Z", 5, date, ZoneOffset.UTC),
                Arguments.of("2022-12-01T08:59:59.999999999Z", 9, date, ZoneOffset.UTC),
                Arguments.of("2022-12-01T09:59:59.999999999Z", 10, date, ZoneOffset.UTC),
                Arguments.of("2022-12-01T21:59:59.999999999Z", 22, date, ZoneOffset.UTC),
                Arguments.of("2022-11-30T23:59:59.999999999Z", 0, date2, ZoneOffset.UTC),
                Arguments.of("2022-12-01T04:59:59.999999999Z", 5, date2, ZoneOffset.UTC),
                Arguments.of("2022-12-01T08:59:59.999999999Z", 9, date2, ZoneOffset.UTC),
                Arguments.of("2022-12-01T09:59:59.999999999Z", 10, date2, ZoneOffset.UTC),
                Arguments.of("2022-12-01T21:59:59.999999999Z", 22, date2, ZoneOffset.UTC),
                Arguments.of("2022-12-31T23:59:59.999999999Z", 0, date3, ZoneOffset.UTC),
                Arguments.of("2023-01-01T04:59:59.999999999Z", 5, date3, ZoneOffset.UTC),
                Arguments.of("2023-01-01T08:59:59.999999999Z", 9, date3, ZoneOffset.UTC),
                Arguments.of("2022-12-01T09:59:59.999999999Z", 10, date3, ZoneOffset.UTC),
                Arguments.of("2022-12-01T21:59:59.999999999Z", 22, date3, ZoneOffset.UTC),
                            )
        }

        @JvmStatic
        private fun previousOrSameHourArguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("2022-11-13T14:55:00Z", 14, "2022-11-14T09:55:00Z"),
                Arguments.of("2022-11-14T14:55:00Z", 14, "2022-11-14T14:55:00Z"),
                Arguments.of("2022-11-14T14:55:00Z", 14, "2022-11-14T15:55:00Z"),
                Arguments.of("2022-11-13T14:55:00Z", 14, "2022-11-14T00:55:00Z"),
                Arguments.of("2022-10-31T14:55:00Z", 14, "2022-11-01T00:55:00Z"),
                            )

        }
    }

    @Test
    fun testApproximateDurationYears() {
        val date1 = Instant.parse("2022-12-07T00:00:00.00Z")
        val date2 = Instant.parse("2023-12-07T00:00:00.00Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(1, result.duration)
        assertEquals(ChronoUnit.YEARS, result.unit)
    }

    @Test
    fun testApproximateDurationYearsRoundToUpperUnit() {
        val date1 = Instant.parse("2022-12-07T00:00:00.00Z")
        val date2 = Instant.parse("2024-07-07T00:00:00.00Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(1, result.duration)
        assertEquals(ChronoUnit.YEARS, result.unit)
    }

    @Test
    fun testApproximateDurationYearsRoundToUpperUnitLessThen6Months() {
        val date1 = Instant.parse("2022-12-07T00:00:00.00Z")
        val date2 = Instant.parse("2024-03-07T00:00:00.00Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(1, result.duration)
        assertEquals(ChronoUnit.YEARS, result.unit)
    }

    @Test
    fun testApproximateDurationMonths() {
        val date1 = Instant.parse("2022-12-07T00:00:00.00Z")
        val date2 = Instant.parse("2023-12-06T00:00:00.00Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(11, result.duration)
        assertEquals(ChronoUnit.MONTHS, result.unit)
    }

    @Test
    fun testApproximateDurationMonthsRoundToUpperUnitLessThen15Days() {
        val date1 = Instant.parse("2022-12-07T00:00:00.00Z")
        val date2 = Instant.parse("2023-11-15T00:00:00.00Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(11, result.duration)
        assertEquals(ChronoUnit.MONTHS, result.unit)
    }

    @Test
    fun testApproximateDurationMonthsRoundToUpperUnit() {
        val date1 = Instant.parse("2022-12-07T00:00:00.00Z")
        val date2 = Instant.parse("2023-01-25T00:00:00.00Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(1, result.duration)
        assertEquals(ChronoUnit.MONTHS, result.unit)
    }

    @Test
    fun testApproximateDurationDays() {
        val date1 = Instant.parse("2022-12-07T00:00:00.00Z")
        val date2 = Instant.parse("2023-01-01T00:00:00.00Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(25, result.duration)
        assertEquals(ChronoUnit.DAYS, result.unit)
    }

    @Test
    fun testApproximateDurationDaysToUpperUnit() {
        val date1 = Instant.parse("2022-12-07T00:00:00.00Z")
        val date2 = Instant.parse("2023-01-01T23:00:00.00Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(25, result.duration)
        assertEquals(ChronoUnit.DAYS, result.unit)
    }

    @Test
    fun testApproximateDurationHours() {
        val date1 = Instant.parse("2022-12-07T00:00:00.00Z")
        val date2 = Instant.parse("2022-12-07T05:50:00.00Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(5, result.duration)
        assertEquals(ChronoUnit.HOURS, result.unit)
    }

    @Test
    fun testApproximateDurationMinutes() {
        val date1 = Instant.parse("2022-12-07T00:00:00.00Z")
        val date2 = Instant.parse("2022-12-07T00:50:00.00Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(50, result.duration)
        assertEquals(ChronoUnit.MINUTES, result.unit)
    }

    @Test
    fun testApproximateDurationSeconds() {
        val date1 = Instant.parse("2022-12-07T00:00:00.00Z")
        val date2 = Instant.parse("2022-12-07T00:00:44.50Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(44, result.duration)
        assertEquals(ChronoUnit.SECONDS, result.unit)
    }

    @Test
    fun testApproximateDurationZero() {
        val date1 = Instant.parse("2022-12-07T00:00:44.50Z")
        val date2 = Instant.parse("2022-12-07T00:00:00.00Z")

        val result = DateAcolyte.withLondonTimezone().getApproximateDurationBetween(date1, date2);

        assertEquals(0, result.duration)
        assertEquals(ChronoUnit.MINUTES, result.unit)
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