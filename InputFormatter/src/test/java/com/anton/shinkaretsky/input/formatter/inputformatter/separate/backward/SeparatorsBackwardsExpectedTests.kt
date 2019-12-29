package com.anton.shinkaretsky.input.formatter.inputformatter.separate.backward

import org.junit.Test

class SeparatorsBackwardsExpectedTests {

    @Test
    fun emptyString() {
        assert(separatorsBackwardsExpected(0, 0) == 0)
        assert(separatorsBackwardsExpected(0, 1) == 0)
        assert(separatorsBackwardsExpected(0, 2) == 0)
        assert(separatorsBackwardsExpected(0, 3) == 0)
    }

    @Test
    fun zeroSeparators() {
        assert(separatorsBackwardsExpected(1, 2) == 0)
        assert(separatorsBackwardsExpected(2, 3) == 0)
        assert(separatorsBackwardsExpected(3, 4) == 0)
        assert(separatorsBackwardsExpected(4, 5) == 0)
    }

    @Test
    fun oneSeparator() {
        assert(separatorsBackwardsExpected(3, 2) == 1)
        assert(separatorsBackwardsExpected(4, 2) == 1)
        assert(separatorsBackwardsExpected(4, 3) == 1)
        assert(separatorsBackwardsExpected(5, 3) == 1)
        assert(separatorsBackwardsExpected(6, 3) == 1)
    }

    @Test
    fun multipleSeparators() {
        assert(separatorsBackwardsExpected(5, 2) == 2)
        assert(separatorsBackwardsExpected(6, 2) == 2)
        assert(separatorsBackwardsExpected(7, 3) == 2)
        assert(separatorsBackwardsExpected(8, 3) == 2)
        assert(separatorsBackwardsExpected(9, 3) == 2)
    }

}