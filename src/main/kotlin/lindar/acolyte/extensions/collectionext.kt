package lindar.acolyte.extensions

import java.util.*
import java.util.function.Predicate
import kotlin.collections.ArrayList


fun <T> MutableCollection<T>.removeAndRetrieve(filter: (T) -> Boolean): List<T> {
    Objects.requireNonNull(filter)
    val removedItems = ArrayList<T>()
    val each = this.iterator()
    while (each.hasNext()) {
        val currentItem = each.next()
        if (filter(currentItem)) {
            each.remove()
            removedItems.add(currentItem)
        }
    }
    return removedItems
}

fun <T> MutableCollection<T>.removeFirstAndRetrieve(filter: (T) -> Boolean): T? {
    Objects.requireNonNull(filter)
    val each = this.iterator()
    while (each.hasNext()) {
        val currentItem = each.next()
        if (filter(currentItem)) {
            each.remove()
            return currentItem
        }
    }
    return null
}

fun <T> MutableCollection<T>.removeAllAndRetrieveFirst(filter: (T) -> Boolean): T? {
    Objects.requireNonNull(filter)
    val removedItems = ArrayList<T>()
    val each = this.iterator()
    while (each.hasNext()) {
        val currentItem = each.next()
        if (filter(currentItem)) {
            each.remove()
            removedItems.add(currentItem)
        }
    }
    return removedItems.firstOrNull()
}

