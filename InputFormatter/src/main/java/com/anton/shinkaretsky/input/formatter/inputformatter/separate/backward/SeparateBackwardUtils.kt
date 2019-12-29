package com.anton.shinkaretsky.input.formatter.inputformatter.separate.backward

import com.anton.shinkaretsky.input.formatter.inputformatter.separate.containsSeparators
import com.anton.shinkaretsky.input.formatter.inputformatter.separate.forward.clearAllSymbols

// Formats string, based on passed pattern. Existing separators cleared
// E.g. for a string "12345678" and a group == 3 result would be "12 345 678"
// E.g. for a string "123 4 5678" and a group == 2 result would be "12 34 56 78"
fun String.separateBackward(separator: String, group: Int): String {
    return if (!isCorrectlySeparatedBackward(separator, group)) {
        StringBuilder().apply {
            val clearString = clearAllSymbols(separator)
            append(clearString)

            for (place in clearString.length - group downTo 1 step group) {
                insert(place, separator)
            }
        }.toString()
    } else {
        this
    }
}

// Checks, if string has correct format
// E.g. for " " separator, and a group == 3
// For a string "1 23 45 678" result == false
// For a string "12 345 678 " result == false
// For a string "12 345 678" result == true
fun String.isCorrectlySeparatedBackward(separator: String, group: Int): Boolean {

    if (length < group && contains(separator)) return false

    val clearLength = this.replace(separator, "").length
    val separatorsExpected = separatorsBackwardsExpected(clearLength, group)
    if (containsSeparators(separator) != separatorsExpected) return false

    val firstSeparatorExpectedEnd = length - group
    for (separatorExpectedEnd in firstSeparatorExpectedEnd downTo separator.length step group + separator.length) {
        val separatorExpected =
            this.substring(separatorExpectedEnd - separator.length, separatorExpectedEnd)
        if (separatorExpected != separator) return false
    }

    // Otherwise everything is ok
    return true
}

// Counts, how many separators should be in a string
// E.g. for a string "12 34 56 78" and a group == 2 result == 4
internal fun separatorsBackwardsExpected(clearLength: Int, group: Int): Int {
    if (clearLength == 0) return 0
    if (group == 0) return 0
    if (clearLength < group) return 0

    val divided = clearLength / group
    val mod = clearLength % group

    return if (mod > 0) divided
    else divided - 1
}