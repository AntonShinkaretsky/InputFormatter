package com.anton.shinkaretsky.input.formatter.inputformatter.format.money

import android.text.SpannableString
import android.text.SpannableStringBuilder
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DeleteAllSeparatorsButFirstTests {

    @Test
    fun emptyString() {
        assert(delete("") == "")
    }

    @Test
    fun noSeparators() {
        assert(delete("123456") == "123456")
    }

    @Test
    fun oneSeparator() {
        assert(delete("123,456") == "123,456")
        assert(delete(",123456") == ",123456")
        assert(delete("123456,") == "123456,")
    }

    @Test
    fun multipleSeparators() {
        assert(delete("12,3,456") == "12,3456")
        assert(delete(",123,456") == ",123456")
        assert(delete("123,456,") == "123,456")
        assert(delete("123,4,56") == "123,456")
        assert(delete("123,4,56,") == "123,456")
    }

    private fun delete(string: String): String {
        val ssb = SpannableStringBuilder(string)
        ssb.deleteAllSeparatorsButFirst(","[0])
        return SpannableString.valueOf(ssb).toString()
    }

}