package com.anton.shinkaretsky.input.formatter.inputformatter.format.money

import android.text.Editable
import com.anton.shinkaretsky.input.formatter.inputformatter.separate.backward.isCorrectlySeparatedBackward
import com.anton.shinkaretsky.input.formatter.inputformatter.separate.backward.separateBackward
import java.util.*

internal fun Editable.removeFirstZerosIfHasMoney(coinsSeparator: Char) {
    while (
        this.firstOrNull() == "0"[0]
        && this.getOrNull(1) != null
        && this.getOrNull(1) != coinsSeparator
    ) {
        this.delete(0, 1)
    }
}

internal fun Editable.replaceAllCharsWithSeparator(
    charsToReplace: String,
    replaceWith: Char
) {
    while (this.containsOneOf(charsToReplace)) {
        var index = -1
        this.forEachIndexed { i, c -> if (charsToReplace.contains(c)) index = i }
        this.delete(index, index + 1)
        this.insert(index, replaceWith.toString())
    }
}

internal fun Editable.containsOneOf(chars: String): Boolean {
    chars.forEach { char -> if (this.contains(char)) return true }
    return false
}

internal fun Editable.addZeroIfStartsWithSeparator(coinsSeparator: Char) {
    if (this.firstOrNull() == coinsSeparator) {
        this.insert(0, "0")
    }
}

internal fun Editable.deleteAllButAllowedSymbols(allowed: String) {
    while (this.find { it.isAllowed(allowed).not() } != null) {
        var index = -1
        this.forEachIndexed { i, c -> if (c.isAllowed(allowed).not()) index = i }
        this.delete(index, index + 1)
    }
}

internal fun Editable.deleteAllSeparatorsButFirst(coinsSeparator: Char) {
    val separators = LinkedList<Int>()
    forEachIndexed { i, c ->
        if (c == coinsSeparator) {
            separators.add(i)
            if (separators.size > 1) return@forEachIndexed
        }
    }
    if (separators.size > 1) {
        this.delete(separators[1], separators[1] + 1)
        deleteAllSeparatorsButFirst(coinsSeparator)
    }
}

internal fun Editable.trimTwoDecimals(decimalSeparator: Char) {
    if (contains(decimalSeparator) && toString().substringAfter(decimalSeparator).length > 2) {
        this.delete(
            indexOf(decimalSeparator) + 3, // because of 1 separator and 2 symbols
            this.length
        )
    }
}

internal fun Editable.separateMoney(
    moneySeparators: Char,
    decimalsSeparator: Char,
    group: Int
) {

    while (this.contains(moneySeparators)) {
        var index = -1
        forEachIndexed { i, c -> if (c == moneySeparators) index = i }
        delete(index, index + 1)
    }

    val moneyWithoutDecimals = this.toString().substringBefore(decimalsSeparator)

    if (
        moneyWithoutDecimals.length > group
        && moneyWithoutDecimals
            .reversed()
            .isCorrectlySeparatedBackward(moneySeparators.toString(), group)
            .not()
    ) {
        val dividedString = moneyWithoutDecimals.separateBackward(moneySeparators.toString(), group)
        replace(0, moneyWithoutDecimals.length, dividedString)
    }
}

internal fun Char.isAllowed(allowed: String): Boolean {
    allowed.forEach { if (this == it) return true }
    return false
}