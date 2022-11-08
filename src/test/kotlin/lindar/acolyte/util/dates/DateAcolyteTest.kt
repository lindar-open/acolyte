package lindar.acolyte.util.dates

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mockStatic
import java.time.*
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
            return Stream.of(
                Arguments.of("2022-11-08T00:00:00Z", 0, date, ZoneOffset.UTC),
                Arguments.of("2022-11-08T05:00:00Z", 5, date, ZoneOffset.UTC),
                Arguments.of("2022-11-08T09:00:00Z", 9, date, ZoneOffset.UTC),
                Arguments.of("2022-11-07T10:00:00Z", 10, date, ZoneOffset.UTC),
                Arguments.of("2022-11-07T22:00:00Z", 22, date, ZoneOffset.UTC),
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