package lindar.acolyte.vo


data class AccessCredentials (
        val apiUrl: String = "",
        val sessionCookie: String = "",
        val username: String = "",
        val password: String = ""
) {
    constructor(apiUrl: String, username: String, password: String) : this(apiUrl, "", username, password)
    constructor(apiUrl: String, sessionCookie: String) : this(apiUrl, sessionCookie, "", "")
}