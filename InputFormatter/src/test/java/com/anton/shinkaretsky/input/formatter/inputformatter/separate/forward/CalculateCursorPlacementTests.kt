package com.anton.shinkaretsky.input.formatter.inputformatter.separate.forward

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculateCursorPlacementTests {

    @Test
    fun add() {
        // 1 added in the start, and will be placed like this "1 23 45 6"
        assertEquals(
            1,
            calculateCursorPlacement(
                "12 34 56",
                0,
                1,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )

        // 3 added in the start, and will be placed like this "1 23 45 6"
        assertEquals(
            4,
            calculateCursorPlacement(
                "12 34 56",
                3,
                1,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )

        // 6 added in the end, and will be placed like this "1 23 45 6"
        assertEquals(
            9,
            calculateCursorPlacement(
                "1 23 456",
                7,
                1,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )

        // 1 added in the end, and will be placed like this "1"
        assertEquals(
            1,
            calculateCursorPlacement(
                "1",
                0,
                1,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )
    }

    @Test
    fun delete() {
        // 4 deleted in the end, and will be placed like this "1 23"
        assertEquals(
            4,
            calculateCursorPlacement(
                "1 23",
                0,
                4,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )

        // 4 deleted in the middle, and will be placed like this "1 23 5 6"
        assertEquals(
            4,
            calculateCursorPlacement(
                "1 23 5 6",
                5,
                0,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )

        // 1 deleted in the start, and will be placed like this " 23 45 6"
        assertEquals(
            0,
            calculateCursorPlacement(
                " 23 45 6",
                0,
                0,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )
    }


    @Test
    fun paste() {
        // 123456789 pasted in empty, and will be placed like this "1 23 45 67 89"
        assertEquals(
            13,
            calculateCursorPlacement(
                "123456789",
                0,
                9,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )

        // 3 45 pasted in the middle, and will be placed like this "1 23 45 67 89"
        assertEquals(
            7,
            calculateCursorPlacement(
                "1 23 456 78 9",
                3,
                4,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )

        // 345 pasted in the middle, and will be placed like this "1 23 45 67 89"
        assertEquals(
            7,
            calculateCursorPlacement(
                "1 23456 78 9",
                3,
                3,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )

        // 123 pasted in the start, and will be placed like this "1 23 45 67 89"
        assertEquals(
            4,
            calculateCursorPlacement(
                "1234 56 78 9",
                0,
                3,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )

        // 789 pasted in the end, and will be placed like this "1 23 45 67 89"
        assertEquals(
            13,
            calculateCursorPlacement(
                "1 23 45 6789",
                9,
                3,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )
    }

    @Test
    fun change() {
        // 45 changed with 789 in a string "1 23 45 67 89", and will be placed like this "1 23 78 96 78 9"
        assertEquals(
            9,
            calculateCursorPlacement(
                "1 23 789 67 89",
                5,
                3,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )
    }

    @Test
    fun cut() {
        // 378 cut from a string "1 23 78 96 78 9", and will be placed like this "1 29 67 89"
        assertEquals(
            3,
            calculateCursorPlacement(
                "1 2 96 78 9",
                3,
                0,
                " ",
                intArrayOf(1, 2, 2, 2, 2)
            )
        )
    }

}