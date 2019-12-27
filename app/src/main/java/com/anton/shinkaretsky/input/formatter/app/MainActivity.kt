package com.anton.shinkaretsky.input.formatter.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anton.shinkaretsky.input.formatter.inputformatter.separate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        "22222".separate("-", intArrayOf(2,2,2))
    }

}