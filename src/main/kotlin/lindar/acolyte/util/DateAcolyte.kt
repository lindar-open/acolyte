package lindar.acolyte.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

object DateAcolyte {
    private const val ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"
    private val formatter = DateTimeFormatter.ofPattern(ISO_DATE_FORMAT)

    @JvmStatic
    fun currentTime(): String {
        return LocalDateTime.now().format(formatter)
    }

    @JvmStatic
    fun formatDate(date: Date): String {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(formatter)
    }

    @JvmStatic
    fun formatDate(date: LocalDateTime): String {
        return date.format(formatter)
    }
}