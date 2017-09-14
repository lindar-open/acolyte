package lindar.acolyte.vo


data class AccessCredentials (
        val apiUrl: String,
        val username: String = "",
        val password: String = "",
        val sessionCookie: String = "",
        val authHeader: String = ""
) {
    companion object {
        fun withUsernameAndPassword(apiUrl: String, username: String, password: String): AccessCredentials {
            return AccessCredentials(apiUrl = apiUrl, username = username, password = password)
        }
        fun withAuthHeader(apiUrl: String, authHeader: String): AccessCredentials {
            return AccessCredentials(apiUrl = apiUrl, authHeader = authHeader)
        }
        fun withSessionCookie(apiUrl: String, sessionCookie: String): AccessCredentials {
            return AccessCredentials(apiUrl = apiUrl, sessionCookie = sessionCookie)
        }
    }
}