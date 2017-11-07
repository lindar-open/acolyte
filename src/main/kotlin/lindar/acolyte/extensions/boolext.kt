package lindar.acolyte.extensions


fun Boolean?.isTrue(): Boolean {
    return this != null && this == true
}

fun Boolean?.isNotTrue(): Boolean {
    return this == null || this == false
}

fun Boolean?.ifTrue(action: () -> Unit): Boolean? {
    if (this.isTrue()) action()
    return this
}

fun Boolean?.elseIfTrue(action: () -> Unit): Boolean? {
    return this.ifTrue(action)
}

fun Boolean?.ifNotTrue(action: () -> Unit): Boolean? {
    if (this.isNotTrue()) action()
    return this
}

fun Boolean?.elseIfNotTrue(action: () -> Unit): Boolean? {
    return this.ifNotTrue(action)
}

