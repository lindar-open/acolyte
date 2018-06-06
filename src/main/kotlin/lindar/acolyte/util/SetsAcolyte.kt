package lindar.acolyte.util

class SetsAcolyte {
    companion object {

        @JvmStatic fun <T> setOf(vararg elements: T): Set<T> {
            return setOf(*elements)
        }

        @JvmStatic fun <T> hashSetOf(vararg elements: T): MutableSet<T> {
            return hashSetOf(*elements)
        }

        @JvmStatic fun <T> linkedHashSetOf(vararg elements: T): MutableSet<T> {
            return mutableSetOf(*elements)
        }


        @JvmStatic fun containsIgnoreCase(set: Set<String>?, item: String?): Boolean {
            return set.orEmpty().filter { str -> str.equals(item, ignoreCase = true) }.any()
        }

        /**
         * Checks if set is null or empty
        */
        @JvmStatic fun <T> isEmpty(set: Set<T>?): Boolean {
            return set == null || set.isEmpty()
        }

        /**
         * Checks that set is not null and not empty
         */
        @JvmStatic fun <T> isNotEmpty(set: Set<T>?): Boolean {
            return set != null && !set.isEmpty()
        }

        /**
         * If provided set is null returns a new HashSet
         */
        @JvmStatic fun <T> defaultIfNull(set: MutableSet<T>?): MutableSet<T> {
            return set ?: mutableSetOf()
        }

        @JvmStatic fun <T> defaultIfNull(set: MutableSet<T>?, defaultVal: MutableSet<T>): MutableSet<T> {
            return set ?: defaultVal
        }

        @JvmStatic fun <T> defaultIfEmpty(set: MutableSet<T>?, defaultVal: MutableSet<T>): MutableSet<T> {
            return if (set == null || set.isEmpty()) defaultVal else set
        }

        @JvmStatic fun <T> hasOnlyOneItem(set: Set<T?>?): Boolean {
            return set?.size == 1
        }

        @JvmStatic fun <T> hasOnlyOneNonNullItem(set: Set<T?>?): Boolean {
            // we use sequence cause it has lazy evaluation
            return set?.asSequence()?.filter { it != null }?.take(2)?.count() == 1
        }
    }
}