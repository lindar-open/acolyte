package lindar.acolyte.validator


class UsernameValidator {
    companion object {
        private val USERNAME_PATTERN = """"^[a-zA-Z0-9._-]{3,20}$"""

        /**
         * Validate username - 3 to 20 characters with any lowercase or uppercase character, digit or special symbol “_-” only

         * @param username username for validation
         * *
         * @return true valid username, false invalid username
         */
        @JvmStatic
        fun validate(username: String): Boolean {
            if (username.isBlank()) {
                return false
            }
            return USERNAME_PATTERN.toRegex().matches(username)
        }
    }
}