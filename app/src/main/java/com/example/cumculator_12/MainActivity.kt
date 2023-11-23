package com.example.cumculator_12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun allClearButton(view: View) {}
    fun equalsButton(view: View) {}
    fun eraseButton(view: View) {}
    fun symbolAction(view: View) {}
    fun numberAction(view: View) {}
}