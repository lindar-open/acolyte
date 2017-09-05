package lindar.acolyte.extensions


fun Boolean?.isTrue(): Boolean {
    return this != null && this == true
}

fun Boolean?.isNotTrue(): Boolean {
    return this == null || this == false
}