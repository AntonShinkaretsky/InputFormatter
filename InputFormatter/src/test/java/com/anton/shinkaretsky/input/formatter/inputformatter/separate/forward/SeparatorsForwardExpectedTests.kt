package com.anton.shinkaretsky.input.formatter.inputformatter.separate.forward

import org.junit.Test

class SeparatorsForwardExpectedTests {

    @Test
    fun test() {
        assert(4 == separatorsForwardExpected(
            14,
            intArrayOf(1, 2, 3, 5, 4)
        )
        ) // 1 23 456 78912 345
        assert(3 == separatorsForwardExpected(
            16,
            intArrayOf(4, 4, 4, 4)
        )
        ) // 1234 5678 1234 5678
        assert(4 == separatorsForwardExpected(
            17,
            intArrayOf(4, 4, 4, 4, 1)
        )
        ) // 1234 5678 1234 5678 1
        assert(4 == separatorsForwardExpected(
            17,
            intArrayOf(4, 4, 4, 4)
        )
        ) // 1234 5678 1234 5678 1
        assert(7 == separatorsForwardExpected(
            15,
            intArrayOf(1, 1, 1, 1, 1, 1, 1)
        )
        ) // 1 2 3 4 5 6 7 89123456
        assert(0 == separatorsForwardExpected(
            1,
            intArrayOf(1, 10, 2, 3)
        )
        ) // 1
        assert(1 == separatorsForwardExpected(
            5,
            intArrayOf(1, 10, 2, 3)
        )
        ) // 1 2345
        assert(4 == separatorsForwardExpected(
            17,
            intArrayOf(1, 10, 2, 3)
        )
        ) // 1 2345678912 34 567 8
        assert(3 == separatorsForwardExpected(
            6,
            intArrayOf(0, 1, 2, 3)
        )
        ) //  1 23 456
        assert(3 == separatorsForwardExpected(
            6,
            intArrayOf(1, 2, 0, 3)
        )
        ) // 1 23  456
        assert(0 == separatorsForwardExpected(
            0,
            intArrayOf(1, 2, 0, 3, 10)
        )
        )
        assert(3 == separatorsForwardExpected(
            8,
            intArrayOf(1, 2, 3, 2)
        )
        ) // 1 23 456 78
    }

}