package com.anton.shinkaretsky.input.formatter.inputformatter.separate

import org.junit.Test

class ContainsSeparatorsTests {

    @Test
    fun oneSymbolSeparator() {
        assert(3 == "1 23 456 78".containsSeparators(" "))
        assert(4 == "1 23 456 78 ".containsSeparators(" "))
        assert(4 == " 1 23 456 78".containsSeparators(" "))
        assert(5 == " 1 23 456 78 ".containsSeparators(" "))
        assert(0 == "12345678".containsSeparators(" "))
        assert(0 == "".containsSeparators(" "))
    }

    @Test
    fun multiSymbolSeparators() {
        assert(0 == "1 23 456 78".containsSeparators("  "))
        assert(1 == "1 23  456 78".containsSeparators("  "))
        assert(0 == "1 23  456  -78".containsSeparators(" - "))
        assert(1 == "1 23  456 - 78".containsSeparators(" - "))
        assert(3 == "1 - 23 - 456 - 78".containsSeparators(" - "))
        assert(4 == " - 1 - 23 - 456 - 78".containsSeparators(" - "))
    }

    @Test
    fun noSeparators_butSymbolsFromMultiSymbolSeparators() {
        assert(0 == "1 23 - 456 =*-78".containsSeparators("-*="))
        assert(0 == "1 23=  456 - *78".containsSeparators("-*="))
        assert(0 == "1 -*- 23 - 456 - 78".containsSeparators("-*="))
        assert(0 == " - 1 - 23 =*=* 456 - 78".containsSeparators("-*="))
    }

}