package lindar.acolyte.validator


class EmailValidator {
    companion object {
        private val EMAIL_PATTERN = """"^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,20}$"""

        /**
         * Validate email - Minimum 8 chars, at least one digit, lower case char, upper case char
         * No more than 256 chars allowed
         * @param email password for validation
         * *
         * @return true valid email, false invalid email
         */
        @JvmStatic
        @JvmOverloads fun validate(email: String, emailRegex: String = EMAIL_PATTERN): Boolean {
            if (email.isBlank()) {
                return false
            }
            return EMAIL_PATTERN.toRegex().matches(email.toLowerCase())
        }
    }
}