package lindar.acolyte.util

import java.util.Optional

class ListsAcolyte {
    companion object {

        @JvmStatic fun <T> listOf(vararg elements: T): List<T> {
            return kotlin.collections.listOf(*elements)
        }

        @JvmStatic fun <T> arrayListOf(vararg elements: T): MutableList<T> {
            return kotlin.collections.mutableListOf(*elements)
        }


        @JvmStatic fun containsIgnoreCase(list: List<String>?, item: String?): Boolean {
            return list.orEmpty().filter { str -> str.equals(item, ignoreCase = true) }.any()
        }

        /**
         * Checks if list is null or empty
        */
        @JvmStatic fun <T> isEmpty(list: List<T>?): Boolean {
            return list == null || list.isEmpty()
        }

        /**
         * Checks that list is not null and not empty
         */
        @JvmStatic fun <T> isNotEmpty(list: List<T>?): Boolean {
            return list != null && !list.isEmpty()
        }

        /**
         * If provided list is null returns a new ArrayList
         */
        @JvmStatic fun <T> defaultIfNull(list: MutableList<T>?): MutableList<T> {
            return list ?: ArrayList<T>()
        }

        @JvmStatic fun <T> defaultIfNull(list: MutableList<T>?, defaultVal: MutableList<T>): MutableList<T> {
            return list ?: defaultVal
        }

        @JvmStatic fun <T> defaultIfEmpty(list: MutableList<T>?, defaultVal: MutableList<T>): MutableList<T> {
            return if (list == null || list.isEmpty()) defaultVal else list
        }

        @JvmStatic fun <T> hasOnlyOneItem(list: List<T?>?): Boolean {
            return list?.size == 1
        }

        @JvmStatic fun <T> hasOnlyOneNonNullItem(list: List<T?>?): Boolean {
            // we use sequence cause it has lazy evaluation
            return list?.asSequence()?.filter { it != null }?.take(2)?.count() == 1
        }

        @JvmStatic fun <T> getFirstItemIfExists(list: List<T?>?): Optional<T> {
            return Optional.ofNullable(list?.firstOrNull())
        }

        @JvmStatic fun <T> getFirstNonNullItemIfExists(list: List<T?>?): Optional<T> {
            return Optional.ofNullable(list?.find { it != null })
        }

        @JvmStatic fun <T> getLastItemIfExists(list: List<T?>?): Optional<T> {
            return Optional.ofNullable(list?.lastOrNull())
        }

        @JvmStatic fun <T> getLastNonNullItemIfExists(list: List<T?>?): Optional<T> {
            return Optional.ofNullable(list?.findLast { it != null })
        }
    }
}