package com.example.cumculator_12

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var workingView: TextView
    private lateinit var resultView: TextView

    private var input: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workingView = findViewById(R.id.workingView)
        resultView = findViewById(R.id.resultView)
    }


    fun onNumberClick(view: View) {
        val button = view as Button
        input += button.text
        updateWorkingView()
    }

    fun onSymbolClick(view: View) {
        val button = view as Button
        input += " " + button.text + " "
        updateWorkingView()
    }

    fun onClearClick(view: View) {
        input = ""
        updateWorkingView()
    }

    fun onEraseClick(view: View) {
        if (input.isNotEmpty()) {
            input = input.substring(0, input.length - 1)
            updateWorkingView()
        }
    }


    fun onEqualsClick(view: View) {
        try {
            val result = evaluateExpression(input)
            resultView.text = result.toString()
        } catch (e: Exception) {
            resultView.text = "Error"
            e.printStackTrace()
        }
    }

    private fun evaluateExpression(expression: String): Double {
        try {
            val result = ExpressionBuilder(expression).build().evaluate()
            return result
        } catch (e: Exception) {
            throw e
        }
    }

    private fun updateWorkingView() {
        workingView.text = input
    }
}
