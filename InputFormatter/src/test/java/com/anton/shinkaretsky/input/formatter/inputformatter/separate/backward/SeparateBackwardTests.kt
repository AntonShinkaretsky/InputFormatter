package com.anton.shinkaretsky.input.formatter.inputformatter.separate.backward

import org.junit.Test

class SeparateBackwardTests {

    @Test
    fun emptyString() {
        assert("".separateBackward(" ", 2) == "")
    }

    @Test
    fun sourceHasNoSeparator_SingleSymbolSeparator() {
        assert("12345678".separateBackward(" ", 2) == "12 34 56 78")
        assert("012345678".separateBackward(" ", 2) == "0 12 34 56 78")

        assert("2345678".separateBackward(" ", 3) == "2 345 678")
        assert("12345678".separateBackward(" ", 3) == "12 345 678")
        assert("012345678".separateBackward(" ", 3) == "012 345 678")
    }

    @Test
    fun sourceHasSeparators_SingleSymbolSeparator() {
        assert("12 34 5 678".separateBackward(" ", 2) == "12 34 56 78")
        assert("1 23 456 78 ".separateBackward(" ", 2) == "12 34 56 78")
        assert("012 34 5 678".separateBackward(" ", 2) == "0 12 34 56 78")
        assert("01 23 456 78 ".separateBackward(" ", 2) == "0 12 34 56 78")

        assert("2 34 5 678".separateBackward(" ", 3) == "2 345 678")
        assert("12 34 5 678".separateBackward(" ", 3) == "12 345 678")
        assert("1 23 456 78 ".separateBackward(" ", 3) == "12 345 678")
        assert("012 34 5 678".separateBackward(" ", 3) == "012 345 678")
        assert("012 34 5 678".separateBackward(" ", 3) == "012 345 678")
        assert("01 23 456 78 ".separateBackward(" ", 3) == "012 345 678")
    }

    @Test
    fun sourceHasNoSeparator_MultipleSymbolSeparator() {
        assert("12345678".separateBackward("-*", 2) == "12-*34-*56-*78")
        assert("012345678".separateBackward("-*", 2) == "0-*12-*34-*56-*78")

        assert("2345678".separateBackward("-*=", 3) == "2-*=345-*=678")
        assert("12345678".separateBackward("-*=", 3) == "12-*=345-*=678")
        assert("012345678".separateBackward("-*=", 3) == "012-*=345-*=678")
    }

    @Test
    fun sourceHasSeparators_MultipleSymbolSeparator() {
        assert("12-*=34-*=5-*=678".separateBackward("-*=", 2) == "12-*=34-*=56-*=78")
        assert("1-*=23-*=456-*=78-*=".separateBackward("-*=", 2) == "12-*=34-*=56-*=78")
        assert("012-*=34-*=5-*=678".separateBackward("-*=", 2) == "0-*=12-*=34-*=56-*=78")
        assert("01-*=23-*=456-*=78-*=".separateBackward("-*=", 2) == "0-*=12-*=34-*=56-*=78")

        assert("2-*34-*5-*678".separateBackward("-*", 3) == "2-*345-*678")
        assert("12-*34-*5-*678".separateBackward("-*", 3) == "12-*345-*678")
        assert("1-*23-*456-*78-*".separateBackward("-*", 3) == "12-*345-*678")
        assert("012-*34-*5-*678".separateBackward("-*", 3) == "012-*345-*678")
        assert("012-*34-*5-*678".separateBackward("-*", 3) == "012-*345-*678")
        assert("01-*23-*456-*78-*".separateBackward("-*", 3) == "012-*345-*678")
    }

}