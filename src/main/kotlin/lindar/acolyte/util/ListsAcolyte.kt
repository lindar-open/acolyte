package lindar.acolyte.util

class ListsAcolyte {
    companion object {
        @JvmStatic
        fun containsIgnoreCase(list: List<String>?, item: String?): Boolean {
            return list.orEmpty().filter { str -> str.equals(item, ignoreCase = true) }.any()
        }

        /**
         * Checks if list is null or empty
         * @param <T>
         * *
         * @param list
         * *
         * @return
        </T> */
        @JvmStatic
        fun <T> isEmpty(list: List<T>?): Boolean {
            return list == null || list.isEmpty()
        }

        /**
         * Checks that list is not null and not empty
         * @param <T>
         * *
         * @param list
         * *
         * @return
        </T> */
        @JvmStatic
        fun <T> isNotEmpty(list: List<T>?): Boolean {
            return list != null && !list.isEmpty()
        }

        /**
         * If provided list is null returns a new ArrayList
         * @param <T>
         * *
         * @param list
         * *
         * @return
        </T> */
        @JvmStatic
        fun <T> defaultIfNull(list: MutableList<T>?): MutableList<T> {
            return list ?: ArrayList<T>()
        }

        @JvmStatic
        fun <T> defaultIfNull(list: MutableList<T>?, defaultVal: MutableList<T>): MutableList<T> {
            return list ?: defaultVal
        }

        @JvmStatic
        fun <T> defaultIfEmpty(list: MutableList<T>?, defaultVal: MutableList<T>): MutableList<T> {
            return if (list == null || list.isEmpty()) defaultVal else list
        }
    }
}