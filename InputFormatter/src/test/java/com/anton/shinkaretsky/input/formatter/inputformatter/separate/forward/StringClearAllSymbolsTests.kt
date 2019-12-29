package com.anton.shinkaretsky.input.formatter.inputformatter.separate.forward

import org.junit.Test

class StringClearAllSymbolsTests {

    @Test
    fun clearsCorrectly_SingleCharacter() {
        assert("12345678" == "12 34 5 678".clearAllSymbols(" "))
        assert("12345678" == " 12 34 5 678".clearAllSymbols(" "))
        assert("12345678" == "12345678".clearAllSymbols(" "))
    }

    @Test
    fun clearsCorrectly_MultipleCharacters() {
        assert("12345678" == "12-*-34-*-5-*-678".clearAllSymbols("-*-"))
        assert("12345678" == "1-234-*56--78".clearAllSymbols("-*-"))
    }

    @Test
    fun clearsCorrectly_OnlyCharactersToDelete() {
        assert("" == "".clearAllSymbols(" "))
        assert("" == " ".clearAllSymbols(" "))
        assert("" == "  ".clearAllSymbols(" "))
    }

}