package com.anton.shinkaretsky.input.formatter.inputformatter.format.money

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.setCurrencyFormatter(
    moneySeparators: Char = " "[0],
    decimalSeparator: Char = ","[0],
    moneyGroup: Int = 3,
    charsToReplaceWithSeparator: String = "."
) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            removeTextChangedListener(this)

            s.replaceAllCharsWithSeparator(charsToReplaceWithSeparator, decimalSeparator)
            s.addZeroIfStartsWithSeparator(decimalSeparator)
            s.removeFirstZerosIfHasMoney(decimalSeparator)
            s.deleteAllButAllowedSymbols("${moneySeparators}${decimalSeparator}1234567890")
            s.deleteAllSeparatorsButFirst(decimalSeparator)
            s.trimTwoDecimals(decimalSeparator)
            s.separateMoney(moneySeparators, decimalSeparator, moneyGroup)

            addTextChangedListener(this)
        }
    })
}