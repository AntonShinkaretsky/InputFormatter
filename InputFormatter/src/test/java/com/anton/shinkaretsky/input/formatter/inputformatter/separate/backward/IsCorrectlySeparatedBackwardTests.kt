package com.anton.shinkaretsky.input.formatter.inputformatter.separate.backward

import org.junit.Assert.assertFalse
import org.junit.Test

class IsCorrectlySeparatedBackwardTests {

    @Test
    fun emptyString() {
        assert("".isCorrectlySeparatedBackward(" ", 2))
    }

    @Test
    fun correctlySeparated_SingleSeparator() {
        assert("4 45".isCorrectlySeparatedBackward(" ", 2))
        assert("34 45".isCorrectlySeparatedBackward(" ", 2))
        assert("2 34 45".isCorrectlySeparatedBackward(" ", 2))
        assert("12 34 45".isCorrectlySeparatedBackward(" ", 2))
        assert("3 445".isCorrectlySeparatedBackward(" ", 3))
        assert("23 445".isCorrectlySeparatedBackward(" ", 3))
        assert("123 445".isCorrectlySeparatedBackward(" ", 3))
        assert("3 445 897".isCorrectlySeparatedBackward(" ", 3))
        assert("23 445 897".isCorrectlySeparatedBackward(" ", 3))
        assert("123 445 897".isCorrectlySeparatedBackward(" ", 3))
    }

    @Test
    fun correctlySeparated_MultiSymbolSeparator() {
        assert("4--45".isCorrectlySeparatedBackward("--", 2))
        assert("34--45".isCorrectlySeparatedBackward("--", 2))
        assert("2--34--45".isCorrectlySeparatedBackward("--", 2))
        assert("12--34--45".isCorrectlySeparatedBackward("--", 2))
        assert("3--445".isCorrectlySeparatedBackward("--", 3))
        assert("23--445".isCorrectlySeparatedBackward("--", 3))
        assert("123--445".isCorrectlySeparatedBackward("--", 3))
        assert("3--445--897".isCorrectlySeparatedBackward("--", 3))
        assert("23--445--897".isCorrectlySeparatedBackward("--", 3))
        assert("123--445--897".isCorrectlySeparatedBackward("--", 3))
    }

    @Test
    fun incorrectlySeparated_SingleSeparator() {
        assertFalse(" 45".isCorrectlySeparatedBackward(" ", 2))
        assertFalse("445".isCorrectlySeparatedBackward(" ", 2))
        assertFalse("3445".isCorrectlySeparatedBackward(" ", 2))
        assertFalse("3 445".isCorrectlySeparatedBackward(" ", 2))
        assertFalse("3 44 5".isCorrectlySeparatedBackward(" ", 2))
        assertFalse("34 45 ".isCorrectlySeparatedBackward(" ", 2))
        assertFalse(" 34 45".isCorrectlySeparatedBackward(" ", 2))
        assertFalse("34  45".isCorrectlySeparatedBackward(" ", 2))

        assertFalse("3 445 ".isCorrectlySeparatedBackward(" ", 3))
        assertFalse("3  445".isCorrectlySeparatedBackward(" ", 3))
        assertFalse("234 45".isCorrectlySeparatedBackward(" ", 3))
        assertFalse(" 123 445".isCorrectlySeparatedBackward(" ", 3))
        assertFalse("123 445 ".isCorrectlySeparatedBackward(" ", 3))
        assertFalse(" 123 445 ".isCorrectlySeparatedBackward(" ", 3))
        assertFalse("1 234 45".isCorrectlySeparatedBackward(" ", 3))
        assertFalse("1 23445".isCorrectlySeparatedBackward(" ", 3))
    }

    @Test
    fun incorrectlySeparated_MultiSymbolSeparator() {
        assertFalse("--45".isCorrectlySeparatedBackward("--", 2))
        assertFalse("445".isCorrectlySeparatedBackward("--", 2))
        assertFalse("3445".isCorrectlySeparatedBackward("--", 2))
        assertFalse("3--445".isCorrectlySeparatedBackward("--", 2))
        assertFalse("3--44--5".isCorrectlySeparatedBackward("--", 2))
        assertFalse("34--45--".isCorrectlySeparatedBackward("--", 2))
        assertFalse("--34--45".isCorrectlySeparatedBackward("--", 2))
        assertFalse("34----45".isCorrectlySeparatedBackward("--", 2))

        assertFalse("3--445--".isCorrectlySeparatedBackward("--", 3))
        assertFalse("3----445".isCorrectlySeparatedBackward("--", 3))
        assertFalse("234--45".isCorrectlySeparatedBackward("--", 3))
        assertFalse("--123--445".isCorrectlySeparatedBackward("--", 3))
        assertFalse("123--445--".isCorrectlySeparatedBackward("--", 3))
        assertFalse("--123--445--".isCorrectlySeparatedBackward("--", 3))
        assertFalse("1--234--45".isCorrectlySeparatedBackward("--", 3))
        assertFalse("1--23445".isCorrectlySeparatedBackward("--", 3))

        assertFalse("3-445--".isCorrectlySeparatedBackward("--", 3))
        assertFalse("234-*45".isCorrectlySeparatedBackward("--", 3))
        assertFalse("==123-==445".isCorrectlySeparatedBackward("--", 3))
    }

}