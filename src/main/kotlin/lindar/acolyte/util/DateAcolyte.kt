package lindar.acolyte.util

import org.joda.time.DateTime
import java.util.Date

object DateAcolyte {
    private val ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"

    @JvmStatic
    fun currentTime(): String {
        return DateTime.now().toString(ISO_DATE_FORMAT)
    }

    @JvmStatic
    fun formatDate(date: Date): String {
        return DateTime(date).toString(ISO_DATE_FORMAT)
    }

    @JvmStatic
    fun formatDate(date: DateTime): String {
        return date.toString(ISO_DATE_FORMAT)
    }
}