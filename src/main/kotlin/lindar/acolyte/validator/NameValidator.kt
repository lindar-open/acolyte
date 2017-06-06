package lindar.acolyte.validator


class NameValidator {
    companion object {
        private val NAME_PATTERN = """"^[a-zA-Z]{1,500}$"""

        /**
         * Validate name - 1 to 500 characters with any lowercase or uppercase character
         * @param name name for validation
         * *
         * @return true valid name, false invalid name
         */
        @JvmStatic
        fun validate(name: String): Boolean {
            if (name.isBlank()) {
                return false
            }
            return NAME_PATTERN.toRegex().matches(name)
        }
    }
}