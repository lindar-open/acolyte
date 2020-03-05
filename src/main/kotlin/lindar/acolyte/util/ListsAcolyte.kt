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


        @JvmStatic fun containsIgnoreCase(list: List<String?>?, item: String?): Boolean {
            return list.orEmpty().any { str -> str.equals(item, ignoreCase = true) }
        }

        @JvmStatic fun containsAnyIgnoreCase(list: List<String?>?, vararg items: String?): Boolean {
            return list.orEmpty().any { str -> items.any { it.equals(str, ignoreCase = true) } }
        }

        @JvmStatic fun containsAllIgnoreCase(list: List<String?>?, vararg items: String?): Boolean {
            return items.all{str -> list.orEmpty().any{it.equals(str, ignoreCase = true)}}
        }

        @JvmStatic fun eachContainsIgnoreCase(list: List<String?>?, item: String?): Boolean {
            return item != null && list.orEmpty().filterNotNull().any { str -> str.contains(item, ignoreCase = true) }
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

        @JvmStatic fun <T> hasMoreThanOneItem(list: List<T?>?): Boolean {
            return list != null && list.size > 1
        }

        @JvmStatic fun <T> emptyOrHasAtMostOneItem(list: List<T?>?): Boolean {
            return list == null || list.size <= 1
        }

        @JvmStatic fun <T> hasOnlyOneNonNullItem(list: List<T?>?): Boolean {
            // we use sequence cause it has lazy evaluation
            return list != null && list.asSequence().filter { it != null }.count() == 1
        }

        @JvmStatic fun <T> hasMoreThanOneNonNullItem(list: List<T?>?): Boolean {
            // we use sequence cause it has lazy evaluation
            return list != null && list.asSequence().filter { it != null }.count() > 1
        }

        @JvmStatic fun <T> hasAtLeastOneNonNullItem(list: List<T?>?): Boolean {
            // we use sequence cause it has lazy evaluation
            return list != null && list.asSequence().filter { it != null }.count() > 0
        }

        @JvmStatic fun <T> emptyOrHasAtMostOneNonNullItem(list: List<T?>?): Boolean {
            // we use sequence cause it has lazy evaluation
            return list == null || list.asSequence().filter { it != null }.count() <= 1
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

        @JvmStatic fun <T, U> mapTo(initialList: List<T?>?, mapper: (T?) -> U): List<U> {
            return initialList?.filterNotNull()?.map(mapper) ?: ArrayList()
        }

        @JvmStatic fun <T, U> mapToIncludeNull(initialList: List<T?>?, mapper: (T?) -> U?): List<U?> {
            return initialList?.map(mapper) ?: ArrayList()
        }
    }
}