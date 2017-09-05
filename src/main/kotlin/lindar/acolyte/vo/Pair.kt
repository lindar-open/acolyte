package lindar.acolyte.vo

class Pair<T, U> private constructor(private val _key: T, private val _value: U) {
    val key: T
        get() = _key

    val value: U
        get() = _value

    companion object {
        @JvmStatic
        fun <T, U> of(key: T, value: U): Pair<T, U> {
            return Pair(key, value)
        }
    }
}
