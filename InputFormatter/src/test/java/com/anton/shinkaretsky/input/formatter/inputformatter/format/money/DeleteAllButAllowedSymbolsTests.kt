package com.anton.shinkaretsky.input.formatter.inputformatter.format.money

import android.text.SpannableString
import android.text.SpannableStringBuilder
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DeleteAllButAllowedSymbolsTests {

    @Test
    fun emptyAndAllowed() {
        assert(allowedOnly("", "") == "")
    }

    @Test
    fun emptyString() {
        assert(allowedOnly("", "123") == "")
    }

    @Test
    fun emptyAllowed() {
        assert(allowedOnly("abc", "") == "")
    }

    @Test
    fun allowedContainsAllSymbols() {
        assert(allowedOnly("123", "123") == "123")
        assert(allowedOnly("123", "321") == "123")
        assert(allowedOnly("1212312313", "321") == "1212312313")
        assert(allowedOnly("1212312313", "321123131") == "1212312313")
    }

    @Test
    fun allowedContainsMoreSymbolsThanNeeded() {
        assert(allowedOnly("123", "1234") == "123")
        assert(allowedOnly("123", "1234") == "123")
        assert(allowedOnly("123", "3214") == "123")
        assert(allowedOnly("1212312313", "3214") == "1212312313")
        assert(allowedOnly("1212312313", "32114231314") == "1212312313")
    }

    @Test
    fun allowedDoesNotContainAllSymbols() {
        assert(allowedOnly("123", "12") == "12")
        assert(allowedOnly("123", "21") == "12")
        assert(allowedOnly("1212312313", "21") == "1212121")
        assert(allowedOnly("1212312313", "211211") == "1212121")
    }

    private fun allowedOnly(string: String, allowed: String): String {
        val ssb = SpannableStringBuilder(string)
        ssb.deleteAllButAllowedSymbols(allowed)
        return SpannableString.valueOf(ssb).toString()
    }

}