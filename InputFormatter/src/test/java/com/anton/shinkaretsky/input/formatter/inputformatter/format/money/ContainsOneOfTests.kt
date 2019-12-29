package com.anton.shinkaretsky.input.formatter.inputformatter.format.money

import android.text.SpannableStringBuilder
import org.junit.Assert.assertFalse
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ContainsOneOfTests {

    @Test
    fun stringDoesContainSuchChars() {
        assert(containsOneOf("123","14"))
        assert(containsOneOf("f42twe4wqw5qc43213","p9lwo"))
    }

    @Test
    fun stringDoesNotContainSuchChars() {
        assertFalse(containsOneOf("123",""))
        assertFalse(containsOneOf("123","456"))
        assertFalse(containsOneOf("f42twe4wqw5qc43213","p9o"))
    }

    private fun containsOneOf(string: String, chars: String): Boolean {
        val ssb = SpannableStringBuilder(string)
        return ssb.containsOneOf(chars)
    }

}