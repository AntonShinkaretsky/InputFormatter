package com.anton.shinkaretsky.input.formatter.inputformatter.format.money

import android.text.SpannableString
import android.text.SpannableStringBuilder
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TrimTwoDecimalsTests {

    @Test
    fun whenStringIsEmpty() {
        assert(trimDecimals("") == "")
    }

    @Test
    fun whenNoDecimals() {
        assert(trimDecimals("123,") == "123,")
        assert(trimDecimals("12345") == "12345")
        assert(trimDecimals("123*45") == "123*45")
    }

    @Test
    fun whenTwoDecimals() {
        assert(trimDecimals("123,45") == "123,45")
    }

    @Test
    fun whenLessThanNeededDecimals() {
        assert(trimDecimals("123,4") == "123,4")
    }

    @Test
    fun whenMoreThanNeededDecimals() {
        assert(trimDecimals("123,456") == "123,45")
        assert(trimDecimals("123,4567") == "123,45")
    }

    @Test
    fun whenMoreThanNeededDecimalsAndSeparators() {
        assert(trimDecimals("123,456,789") == "123,45")
    }

    private fun trimDecimals(string: String): String {
        val ssb = SpannableStringBuilder(string)
        ssb.trimTwoDecimals(","[0])
        return SpannableString.valueOf(ssb).toString()
    }

}