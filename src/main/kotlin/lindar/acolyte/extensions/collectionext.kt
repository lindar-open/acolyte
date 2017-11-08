package lindar.acolyte.extensions

import java.util.*
import java.util.function.Predicate
import kotlin.collections.ArrayList


fun <T> MutableCollection<T>.removeAndRetrieve(filter: Predicate<T>): List<T> {
    Objects.requireNonNull(filter)
    val removedItems = ArrayList<T>()
    val each = this.iterator()
    while (each.hasNext()) {
        val currentItem = each.next()
        if (filter.test(currentItem)) {
            each.remove()
            removedItems.add(currentItem)
        }
    }
    return removedItems
}

fun <T> MutableCollection<T>.removeFirstAndRetrieve(filter: Predicate<T>): T? {
    Objects.requireNonNull(filter)
    val each = this.iterator()
    while (each.hasNext()) {
        val currentItem = each.next()
        if (filter.test(currentItem)) {
            each.remove()
            return currentItem
        }
    }
    return null
}

fun <T> MutableCollection<T>.removeAllAndRetrieveFirst(filter: Predicate<T>): T? {
    Objects.requireNonNull(filter)
    val removedItems = ArrayList<T>()
    val each = this.iterator()
    while (each.hasNext()) {
        val currentItem = each.next()
        if (filter.test(currentItem)) {
            each.remove()
            removedItems.add(currentItem)
        }
    }
    return removedItems.firstOrNull()
}

