package com.anton.shinkaretsky.input.formatter.inputformatter.format.money

import org.junit.Assert.assertFalse
import org.junit.Test

class CharIsAllowedTests {

    @Test
    fun allowedEmpty() {
        assertFalse("1"[0].isAllowed(allowed = ""))
    }

    @Test
    fun allowedContainsThisSymbolOnce() {
        assert("1"[0].isAllowed(allowed = "1"))
        assert("1"[0].isAllowed(allowed = "123"))
        assert("1"[0].isAllowed(allowed = "321"))
    }

    @Test
    fun allowedContainsThisSymbolMultipleTimes() {
        assert("1"[0].isAllowed(allowed = "2341dfs1"))
    }

    @Test
    fun notAllowed() {
        assertFalse("1"[0].isAllowed(allowed = "234dfs"))
        assertFalse("1"[0].isAllowed(allowed = "2"))
    }

}