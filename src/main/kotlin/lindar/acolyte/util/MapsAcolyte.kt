package lindar.acolyte.util

class MapsAcolyte {
    companion object {

        /**
         * Creates a LinkedHashMap
         */
        @JvmStatic @SafeVarargs
        fun <K, V> mapOf(vararg pairs: lindar.acolyte.vo.Pair<K, V>): Map<K, V> {
            return kotlin.collections.mapOf(*pairs.map { Pair(it.key, it.value) }.toTypedArray())
        }

        /**
         * Creates a HashMap
         */
        @JvmStatic @SafeVarargs
        fun <K, V> hashMapOf(vararg pairs: lindar.acolyte.vo.Pair<K, V>): Map<K, V> {
            return kotlin.collections.hashMapOf(*pairs.map { Pair(it.key, it.value) }.toTypedArray())
        }


        /**
         * Checks if map is null or empty
        */
        @JvmStatic fun <K, V> isEmpty(map: Map<K, V>?): Boolean {
            return map == null || map.isEmpty()
        }

        /**
         * Checks that map is not null and not empty
        */
        @JvmStatic fun <K, V> isNotEmpty(map: Map<K, V>?): Boolean {
            return map != null && !map.isEmpty()
        }

        /**
         * If provided map is null returns a new HashMap
         * */
        @JvmStatic fun <K, V> defaultIfNull(map: Map<K, V>?): Map<K, V> {
            return map ?: HashMap()
        }

        @JvmStatic fun <K, V> defaultIfNull(map: Map<K, V>?, defaultVal: Map<K, V>): Map<K, V> {
            return map ?: defaultVal
        }

        @JvmStatic fun <K, V> defaultIfEmpty(map: Map<K, V>?, defaultVal: Map<K, V>): Map<K, V> {
            return if (map == null || map.isEmpty()) defaultVal else map
        }
    }
}