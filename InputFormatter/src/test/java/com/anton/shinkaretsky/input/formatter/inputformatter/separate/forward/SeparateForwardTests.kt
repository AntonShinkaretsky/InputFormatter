package com.anton.shinkaretsky.input.formatter.inputformatter.separate.forward

import org.junit.Test

class SeparateForwardTests {

    @Test
    fun emptyString() {
        assert("".separateForward(" ", intArrayOf(1, 2, 3, 2)) == "")
    }

    @Test
    fun stringHasNoSeparator() {
        assert("12345678".separateForward(" ", intArrayOf(1, 2, 3, 2)) == "1 23 456 78")
    }

    @Test
    fun stringHasSeparators() {
        assert("12 34 5 678".separateForward(" ", intArrayOf(1, 2, 3, 2)) == "1 23 456 78")
        assert("1 23 456 78 ".separateForward(" ", intArrayOf(1, 2, 3, 2)) == "1 23 456 78")
    }

    @Test
    fun havingZeroInsideTheGroups() {
        assert("12345678".separateForward(" ", intArrayOf(0, 1, 2, 3, 2)) == " 1 23 456 78")
        assert("12 34 5 678".separateForward(" ", intArrayOf(0, 1, 2, 3, 2)) == " 1 23 456 78")
        assert("12345678".separateForward(" ", intArrayOf(0, 1, 2, 3, 2)) == " 1 23 456 78")
        assert(" 12 34 5 678".separateForward(" ", intArrayOf(0, 1, 2, 3, 2)) == " 1 23 456 78")
        assert(" 12 34 5 678".separateForward(" ", intArrayOf(1, 2, 0, 3, 2)) == "1 23  456 78")
    }

    @Test
    fun longGroups_WithoutEnoughSymbols() {
        assert("12345678".separateForward(" ", intArrayOf(12, 3, 2)) == "12345678")
        assert("12 34 5 678".separateForward(" ", intArrayOf(12, 3, 2)) == "12345678")
    }

    @Test
    fun longGroups_WithEnoughSymbols() {
        assert(
            "1234567891234567".separateForward(" ", intArrayOf(12, 3, 2)) == "123456789123 456 7"
        )
        assert(
            "12 34 5 6789 1234 56 7 "
                .separateForward(" ", intArrayOf(12, 3, 2)) == "123456789123 456 7"
        )
    }

}