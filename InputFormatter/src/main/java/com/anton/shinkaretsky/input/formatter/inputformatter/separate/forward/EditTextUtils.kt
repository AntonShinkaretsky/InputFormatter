package com.anton.shinkaretsky.input.formatter.inputformatter.separate.forward

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

// Formats EditText, based on passed pattern
// E.g. for a string "12345678" and a group intArrayOf(1,2,3,2) result would be "1 23 456 78"
fun EditText.separateForward(separator: String, groups: IntArray) {

    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            // Formatted string (changes only if doesn't have correct format already)
            val string = s.toString().separateForward(separator, groups)

            // If current EditText string doesn't have correct format, change with formatted
            if (s.toString() != string) {
                setText(string)

                // And place cursor into correct place
                val place =
                    calculateCursorPlacement(
                        s.toString(),
                        start,
                        count,
                        separator,
                        groups
                    )
                setSelection(place)
            }
        }

        override fun afterTextChanged(s: Editable) {}
    })
}

internal fun calculateCursorPlacement(
    string: String,
    start: Int,
    count: Int,
    separator: String,
    groups: IntArray
): Int {
    val clearCount = string.substring(start, start + count).replace(separator, "").length
    val clearStart = string.substring(0, start).replace(separator, "").length
    val sepExp =
        separatorsForwardExpected(
            clearStart + clearCount,
            groups
        )
    return clearCount + clearStart + (separator.length * sepExp)
}