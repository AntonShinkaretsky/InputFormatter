package com.anton.shinkaretsky.input.formatter.inputformatter.format.money

import android.text.SpannableString
import android.text.SpannableStringBuilder
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ReplaceAllCharsWithSeparatorTests {

    @Test
    fun emptyString() {
        assert(replace("", "0", "1"[0]) == "")
    }

    @Test
    fun stringContainsNoCharsToReplace() {
        assert(replace("123", "0", "4"[0]) == "123")
    }

    @Test
    fun stringContainsOneCharToReplace() {
        assert(replace("0123", "0", "4"[0]) == "4123")
    }

    @Test
    fun stringContainsMultipleCharsToReplace() {
        assert(replace("012jgh349t4e0dsf03t", "0t", "4"[0]) == "412jgh34944e4dsf434")
    }

    private fun replace(string: String, charsToReplace: String, replaceWith: Char): String {
        val ssb = SpannableStringBuilder(string)
        ssb.replaceAllCharsWithSeparator(charsToReplace, replaceWith)
        return SpannableString.valueOf(ssb).toString()
    }

}