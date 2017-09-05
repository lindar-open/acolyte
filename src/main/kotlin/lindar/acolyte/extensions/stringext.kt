package lindar.acolyte.extensions


fun String?.defaultIfNull(): String {
    return this ?: ""
}

fun String?.notNullOrBlank(): Boolean {
    return this != null && this.isNotBlank()
}

fun String?.nullOrBlank(): Boolean {
    return this == null || this.isBlank()
}