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
            // Производим замену символов в текущем вводе перед добавлением нового символа
            input = input.replace("×", "*").replace("÷", "/")

            // Добавляем символ только при нажатии "=" или после ввода цифры
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

    fun onRootClick(view: View) {
        input += " √("
        updateWorkingView()
    }

    fun onFactorialClick(view: View) {
        input += "типа Fuckториал"
    }

    fun onDegreesClick(view: View) {
        input += " ^ "
        updateWorkingView()
    }

    fun on69Click(view: View) {
        input += "Bruh! "
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

    fun factorial(n: Int): Long {
        return if (n == 0 || n == 1) {
            1
        } else {
            n * factorial(n - 1)
        }
    }
}
