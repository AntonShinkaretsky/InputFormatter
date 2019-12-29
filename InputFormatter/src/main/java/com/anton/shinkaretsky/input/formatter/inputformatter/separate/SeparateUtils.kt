package com.anton.shinkaretsky.input.formatter.inputformatter.separate

internal fun String.containsSeparators(separator: String): Int {
    val lengthWithoutSeparators = replace(separator, "").length
    return (length - lengthWithoutSeparators) / separator.length
}