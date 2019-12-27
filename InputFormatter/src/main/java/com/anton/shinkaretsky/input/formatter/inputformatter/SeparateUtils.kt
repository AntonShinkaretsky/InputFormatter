package com.anton.shinkaretsky.input.formatter.inputformatter

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.StringBuilder

// Formats EditText, based on passed pattern
// E.g. for a string "12345678" and a group intArrayOf(1,2,3,2) result would be "1 23 456 78"
fun EditText.separate(separator: String, groups: IntArray) {

    val edit = this

    edit.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            // Formatted string (changes only if doesn't have correct format already)
            val string = s.toString().separate(separator, groups)

            // If current EditText string doesn't have correct format, change with formatted
            if (s.toString() != string) {
                edit.setText(string)

                // And place cursor into correct place
                val place = calculateCursorPlacement(s.toString(), start, count, separator, groups)
                edit.setSelection(place)
            }
        }

        override fun afterTextChanged(s: Editable) {}
    })
}

private fun calculateCursorPlacement(string: String, start: Int, count: Int, separator: String, groups: IntArray): Int {
    val clearCount = string.substring(start, start + count).replace(separator, "").length
    val clearStart = string.substring(0, start).replace(separator, "").length
    val sepExp = separatorsExpected(clearStart + clearCount, groups)
    return clearCount + clearStart + (separator.length * sepExp)
}

// Formats string, based on passed pattern. Existing separators ignored
// E.g. for a string "12345678" and a group intArrayOf(1,2,3,2) result would be "1 23 456 78"
// E.g. for a string "123 4 5678" and a group intArrayOf(1,2,3,2) result would be "1 23 456 78"
fun String.separate(separator: String, groups: IntArray): String {

    var string = this

    // If current text doesn't have correct formatting
    if (!this.correctFormat(separator, groups)) {

        val clearString = this.clearAllSymbols(separator)
        val clearLength = clearString.length
        val separatorsExpected = separatorsExpected(clearLength, groups)

        // Initialize default string
        val result = StringBuilder()
        result.append(clearString)

        var placeForSeparator = 0

        // We need to add only as much separators, as long our sting filled by pattern
        // E.g. for a string "1 23 45" and a group intArrayOf(1,2,3,2) we need to add only 2 separators
        for (i in 0 until separatorsExpected) {
            val group = groups[i]

            // Index we should insert separator in - is added groups and added separators
            // E.g. for a string "1 23 456" and a group intArrayOf(1,2,3,2)
            // when we are on a second separator to add ("1 23456"), after second group
            // placeForSeparator would be 1 + 2 (groups) + 1 (separator) = 4, in case our separator length is 1
            placeForSeparator += group
            if (i > 0) placeForSeparator += separator.length

            // We are adding current separator
            val current = result.toString()
            val head = current.substring(0, placeForSeparator)
            val tail = current.substring(placeForSeparator, current.length)

            // And replacing old string with new one - with separator
            result.delete(0, current.length)
            result.append("$head$separator$tail")
        }

        string = result.toString()
    }

    return string
}

private fun String.clearAllSymbols(separator: String): String {
    var result = this
    for (char in separator) {
        result = result.replace(char.toString(), "")
    }
    return result
}

// Checks, if string has correct format
// E.g. for " " separator, and a group intArrayOf(1,2,3,2)
// For a string "1 23 45 678" result == false
// For a string "1 23 456 78 " result == false
// For a string "1 23 456 78" result == true
private fun String.correctFormat(separator: String, groups: IntArray): Boolean {

    // Creating array of indexes, where separators should be placed
    val separatorPlaces = ArrayList<Int>()

    // Represents a place, where current separator should be
    var currentPlace = 0

    for (i in groups.indices) {

        // Index separator should be - is added groups and added separators
        // E.g. for a string "1 23 456" and a group intArrayOf(1,2,3,2)
        // when we are on a second separator to check
        // currentPlace would be 1 + 2 (groups) + 2 (separator) = 4, in case our separator length is 1
        currentPlace += groups[i]
        if (i > 0) currentPlace += separator.length

        separatorPlaces.add(currentPlace)
    }

    for (place in separatorPlaces) {

        // Separator always should be between symbols, so after separators's index there should be more
        // So we check, if sting is longer than the index, and we shouldn't check this separator at all
        if (this.length > place) {

            // If length means that separator should be here, string length should be enough to contain this separator
            if (this.length < place + separator.length) return false

            // If length looks ok, cut symbols there
            val placeSymbols = this.substring(place, place + separator.length)

            // If there is no expected separator, formatting is incorrect
            if (placeSymbols != separator) return false
        }
    }

    // If there should be no separators, but there is any, formatting is incorrect
    if (this.length < groups[0] && this.contains(separator)) return false

    // If there are more separators than expected, formatting is incorrect
    val clearString = this.replace(separator, "")
    val clearLength = clearString.length
    if (this.containsSeparators(separator) > separatorsExpected(clearLength, groups)) return false

    // Otherwise everything is ok
    return true
}

private fun String.containsSeparators(separator: String): Int {
    return Regex(separator).findAll(this).count()
}

// Counts, how many separators should be in a string, to split it for passed groups
// E.g. for a string "1 23 456 78" and a group intArrayOf(1,2,3,2) result == 3
private fun separatorsExpected(clearLength: Int, groups: IntArray): Int {

    // Represents current group
    // E.g. for a group intArrayOf(1,2,3,2) results would be 1,2,3,4
    var currentGroup = 0

    // Represents current end of a group
    // E.g. for a group intArrayOf(1,2,3,2) results would be 1,3,6,8
    var endOfGroup = 0

    // We move through the groups
    for (group in groups) {

        // Update out values
        currentGroup++
        endOfGroup += group

        // If current string length (without separators) matches current group range (without separators)
        // we return separatorsExpected, that is always currentGroup - 1
        // E.g. for a string "1 23 45" and a group intArrayOf(1,2,3,2) result == 2
        // because it currently located in 3 group
        val startOfGroup = endOfGroup - group
        if (clearLength in startOfGroup..endOfGroup) return currentGroup - 1
    }

    // If we pass all the groups, we should return last separator
    // E.g. for a string "1 23 456 78 91234" and a group intArrayOf(1,2,3,2) result == 4
    // because it currently located in 5 group
    return currentGroup
}