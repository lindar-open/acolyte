package lindar.acolyte.validator


class PasswordValidator {
    companion object {
        /*
            ^                 # start-of-string
            (?=.*[0-9])       # a digit must occur at least once
            (?=.*[a-z])       # a lower case letter must occur at least once
            (?=.*[A-Z])       # an upper case letter must occur at least once
            .{8,256}          # between 8 and 256 chars
            $                 # end-of-string
         */
        private val PASS_PATTERN = """"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,256}$"""

        /**
         * Validate password - Minimum 8 chars, at least one digit, lower case char, upper case char
         * No more than 256 chars allowed though... just so we don't save entire blobs here :)

         * @param password password for validation
         * *
         * @return true valid password, false invalid password
         */
        @JvmStatic
        fun validate(password: String): Boolean {
            if (password.isBlank()) {
                return false
            }
            return PASS_PATTERN.toRegex().matches(password)
        }
    }
}