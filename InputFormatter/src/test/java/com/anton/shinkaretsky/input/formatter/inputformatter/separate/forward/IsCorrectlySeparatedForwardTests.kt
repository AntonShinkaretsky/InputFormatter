package com.anton.shinkaretsky.input.formatter.inputformatter.separate.forward

import org.junit.Assert.assertFalse
import org.junit.Test

class IsCorrectlySeparatedForwardTests {

    @Test
    fun common() {
        assert("1 23 456 78".isCorrectlySeparatedForward(" ", intArrayOf(1, 2, 3, 2)))
        assert("1 23 45".isCorrectlySeparatedForward(" ", intArrayOf(1, 2, 3, 2)))
        assert("1 23 453".isCorrectlySeparatedForward(" ", intArrayOf(1, 2, 3, 2)))
        assert("1 234567891234 453 1".isCorrectlySeparatedForward(" ", intArrayOf(1, 12, 3, 2)))
    }

    @Test
    fun emptyString() {
        assert("".isCorrectlySeparatedForward(" ", intArrayOf(1, 2, 3, 2)))
        assert("".isCorrectlySeparatedForward(" ", intArrayOf(0, 2, 3, 2)))
        assert("".isCorrectlySeparatedForward(" ", intArrayOf(120, 2, 3, 2)))
    }

    @Test
    fun withZeroGroups() {

        assert("1 234567891234  453 1".isCorrectlySeparatedForward(" ", intArrayOf(1, 12, 0, 3, 2)))
        assert(" 1 23 453".isCorrectlySeparatedForward(" ", intArrayOf(0, 1, 2, 3, 2)))
        assert("1 23 453".isCorrectlySeparatedForward(" ", intArrayOf(1, 2, 3, 0, 2)))
        assert("1 23  453".isCorrectlySeparatedForward(" ", intArrayOf(1, 2, 0, 3, 2)))
    }

    @Test
    fun multiSymbolSeparators() {
        assert("1 -23 -453".isCorrectlySeparatedForward(" -", intArrayOf(1, 2, 3, 2)))
        assert("=-+1=-+23=-+453".isCorrectlySeparatedForward("=-+", intArrayOf(0, 1, 2, 3, 2)))
        assert("1".isCorrectlySeparatedForward("--", intArrayOf(1, 2, 3, 2)))
        assert("1".isCorrectlySeparatedForward("-+-", intArrayOf(1, 2, 3, 2)))
        assert("".isCorrectlySeparatedForward("--", intArrayOf(1, 2, 3, 2)))
    }

    @Test
    fun incorrectCases() {
        assertFalse(
            "1 23 45 678".isCorrectlySeparatedForward(
                " ",
                intArrayOf(1, 2, 3, 2)
            )
        ) // incorrect dividing
        assertFalse(
            "1 2345678".isCorrectlySeparatedForward(
                " ",
                intArrayOf(1, 2, 3, 2)
            )
        ) // not enough dividers
        assertFalse(
            "1 23 4567".isCorrectlySeparatedForward(
                " ",
                intArrayOf(1, 2, 3, 2)
            )
        ) // not enough dividers
        assertFalse(
            "12 34 5 678".isCorrectlySeparatedForward(
                " ",
                intArrayOf(12, 3, 2)
            )
        ) // should be 0 dividers
        assertFalse(
            "12 34 5 6789012".isCorrectlySeparatedForward(
                " ",
                intArrayOf(12, 3, 2)
            )
        ) // should be 0 dividers
        assertFalse(
            "12 34 5 678901".isCorrectlySeparatedForward(
                " ",
                intArrayOf(12, 3, 2)
            )
        ) // should be 0 dividers
        assertFalse(
            "1 23 456 78 ".isCorrectlySeparatedForward(
                " ",
                intArrayOf(1, 2, 3, 2)
            )
        ) // No need for divider in the end


        assertFalse("1 23 -453".isCorrectlySeparatedForward(" -", intArrayOf(1, 2, 3, 2)))
        assertFalse("1- 23 -453".isCorrectlySeparatedForward(" -", intArrayOf(1, 2, 3, 2)))
        assertFalse("=-+1=+23=-+453".isCorrectlySeparatedForward("=-+", intArrayOf(0, 1, 2, 3, 2)))
        assertFalse("=-+1=-+23=-453".isCorrectlySeparatedForward("=-+", intArrayOf(0, 1, 2, 3, 2)))
        assertFalse("12".isCorrectlySeparatedForward("-+-", intArrayOf(1, 2, 2, 2)))
    }

}