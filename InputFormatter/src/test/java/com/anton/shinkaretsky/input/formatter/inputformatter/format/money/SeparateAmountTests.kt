package com.anton.shinkaretsky.input.formatter.inputformatter.format.money

import android.text.SpannableString
import android.text.SpannableStringBuilder
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SeparateAmountTests {

    private val moneySeparators: Char = " "[0]
    private val decimalsSeparator: Char = ","[0]

    @Test
    fun emptyString() {
        assert(separate("", 3) == "")
    }

    @Test
    fun noSeparationNeeded() {
        assert(separate("1", 3) == "1")
        assert(separate("12", 3) == "12")
        assert(separate("123", 3) == "123")
        assert(separate("123,4", 3) == "123,4")
        assert(separate("123,45", 3) == "123,45")
        assert(separate("12345", 5) == "12345")
        assert(separate("12345,6", 5) == "12345,6")
        assert(separate("12345,67", 5) == "12345,67")
    }

    @Test
    fun separationIsNeeded() {
        assert(separate("12", 1) == "1 2")
        assert(separate("123", 2) == "1 23")
        assert(separate("1 23", 2) == "1 23")
        assert(separate("1234", 3) == "1 234")
        assert(separate("123 4", 3) == "1 234")
        assert(separate("12 34", 3) == "1 234")
        assert(separate("1234,4", 3) == "1 234,4")
        assert(separate("123,45", 3) == "123,45")
        assert(separate("1234,45", 3) == "1 234,45")
        assert(separate("123456", 5) == "1 23456")
        assert(separate("12345,6", 5) == "12345,6")
        assert(separate("123456,6", 5) == "1 23456,6")
        assert(separate("123456,67", 5) == "1 23456,67")
    }

    private fun separate(string: String, group: Int): String {
        val ssb = SpannableStringBuilder(string)
        ssb.separateMoney(moneySeparators, decimalsSeparator, group)
        return SpannableString.valueOf(ssb).toString()
    }

}