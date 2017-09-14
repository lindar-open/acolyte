package lindar.acolyte.vo


data class AccessCredentials (
        val apiUrl: String,
        val username: String = "",
        val password: String = "",
        val sessionCookie: String = "",
        val authHeader: String = ""
) {
    companion object {
        @JvmStatic
        fun withUsernameAndPassword(apiUrl: String, username: String, password: String): AccessCredentials {
            return AccessCredentials(apiUrl = apiUrl, username = username, password = password)
        }
        @JvmStatic
        fun withAuthHeader(apiUrl: String, authHeader: String): AccessCredentials {
            return AccessCredentials(apiUrl = apiUrl, authHeader = authHeader)
        }
        @JvmStatic
        fun withSessionCookie(apiUrl: String, sessionCookie: String): AccessCredentials {
            return AccessCredentials(apiUrl = apiUrl, sessionCookie = sessionCookie)
        }
    }
}