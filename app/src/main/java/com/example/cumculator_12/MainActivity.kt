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
        val symbol = button.text.toString()

        if (symbol == "(") {
            input += " $symbol "
        } else if (symbol == ")") {
            val openBracketCount = input.count { it == '(' }
            val closeBracketCount = input.count { it == ')' }

            if (openBracketCount > closeBracketCount) {
                input += " $symbol "
            }
        } else {

            input = input.replace("×", "*").replace("÷", "/")

            if (input.isNotEmpty() && (input.last().isDigit() || input.last() == ')' || input.last() == '*' || input.last() == '/')) {
                input += " $symbol "
                updateWorkingView()
            }
        }
    }

    fun onClearClick(view: View) {
        input = ""
        updateWorkingView()
        resultView.text = ""
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
        }
    }
    fun onFactorialClick(view: View){
        input += "!"
        updateWorkingView()
    }

    fun onRootClick(view: View) {
        input += " sqrt("
        updateWorkingView()
    }

    fun onLOGClick(view: View) {
        input += "log("
        updateWorkingView()
    }

    fun onCOSClick(view: View) {
        input += "cos("
        updateWorkingView()
    }

    fun onSINClick(view: View) {
        input += "sin("
        updateWorkingView()
    }

    fun onDegreesClick(view: View) {
        input += "^"
        updateWorkingView()
    }

    fun on69Click(view: View) {
        input += "Bruh! "
        updateWorkingView()
    }

    fun onPIClick(view: View) {
        input += "3.14159265358979"
        updateWorkingView()
    }

    private fun evaluateExpression(expression: String): Double {
        try {
            return ExpressionBuilder(expression).build().evaluate()
        } catch (e: Exception) {
            throw e
        }
    }

    private fun updateWorkingView() {
        workingView.text = input
    }
}
