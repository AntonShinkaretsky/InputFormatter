package com.anton.shinkaretsky.input.formatter.inputformatter.format.money

import android.text.SpannableString
import android.text.SpannableStringBuilder
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AddZeroIfStartsWithSeparatorTests {

    @Test
    fun addNull_WhenStartsWithSeparator() {
        assert(addZero(",34") == "0,34")
    }

    @Test
    fun doesNothing_WhenStartsZero() {
        assert(addZero("0,34") == "0,34")
    }

    @Test
    fun doesNothing_WhenStartsWithNumbers() {
        assert(addZero("123,34") == "123,34")
    }

    @Test
    fun doesNothing_WithoutSeparator() {
        assert(addZero("12334") == "12334")
    }

    private fun addZero(string: String): String {
        val ssb = SpannableStringBuilder(string)
        ssb.addZeroIfStartsWithSeparator(coinsSeparator = ","[0])
        return SpannableString.valueOf(ssb).toString()
    }

}