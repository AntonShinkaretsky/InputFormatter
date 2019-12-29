package com.anton.shinkaretsky.input.formatter.inputformatter.format.money

import android.text.SpannableString
import android.text.SpannableStringBuilder
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RemoveFirstZerosIfHasMoneyTests {

    @Test
    fun emptyString() {
        assert(remove("") == "")
    }

    @Test
    fun stringStartsWithNoZeroes_AndContainsMoney() {
        assert(remove("3,12") == "3,12")
        assert(remove("3") == "3")
    }

    @Test
    fun stringStartsWithOneZero_AndContainsMoney() {
        assert(remove("03,12") == "3,12")
        assert(remove("03") == "3")
    }

    @Test
    fun stringStartsWithMultipleZeroes_AndContainsMoney() {
        assert(remove("00003,12") == "3,12")
        assert(remove("00003") == "3")
    }

    @Test
    fun stringStartsWithNoZeroes_ButContainsNoMoney() {
        assert(remove(",12") == ",12")
    }

    @Test
    fun stringStartsWithOneZero_ButContainsNoMoney() {
        assert(remove("0,12") == "0,12")
        assert(remove("0") == "0")
    }

    @Test
    fun stringStartsWithMultipleZeros_ButContainsNoMoney() {
        assert(remove("0000,12") == "0,12")
        assert(remove("0000") == "0")
    }

    private fun remove(string: String): String {
        val ssb = SpannableStringBuilder(string)
        ssb.removeFirstZerosIfHasMoney(coinsSeparator = ","[0])
        return SpannableString.valueOf(ssb).toString()
    }

}