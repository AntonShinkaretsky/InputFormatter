package com.anton.shinkaretsky.input.formatter.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anton.shinkaretsky.input.formatter.inputformatter.format.money.setCurrencyFormatter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edit.setCurrencyFormatter(" ", ",")
    }

}