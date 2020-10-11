package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.calculator.expressions.ExpressionEvaluabilityException
import com.example.calculator.expressions.InfixEvaluator
import com.example.calculator.expressions.parsing.ParsingException

class MainActivity : AppCompatActivity() {

    private val literalButtonIds = listOf(
        R.id.buttonLiteral0,
        R.id.buttonLiteral1,
        R.id.buttonLiteral2,
        R.id.buttonLiteral3,
        R.id.buttonLiteral4,
        R.id.buttonLiteral5,
        R.id.buttonLiteral6,
        R.id.buttonLiteral7,
        R.id.buttonLiteral8,
        R.id.buttonLiteral9,
        R.id.buttonLiteralMultiplication,
        R.id.buttonLiteralPlus,
        R.id.buttonLiteralMinus,
        R.id.buttonLiteralDivision,
        R.id.buttonLiteralLeftBrace,
        R.id.buttonLiteralRightBrace,
        R.id.buttonLiteralPoint
    )

    lateinit var expressionField: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        expressionField = findViewById(R.id.expressionField)
        setLiteralButtons()
        setEqualsButton()
        setBackspaceButton()
    }

    private fun setLiteralButtons() {
        val literalButtons = literalButtonIds.map { findViewById<Button>(it) }
        for (button in literalButtons) {
            val literal = button.text?.toString() ?: ""
            button.setOnClickListener {
                val previousText = (expressionField.text ?: "") as String
                expressionField.text = previousText + literal
            }
        }
    }

    fun setEqualsButton() {
        val equalsButton = findViewById<Button>(R.id.buttonActionEquals)
        equalsButton.setOnClickListener {
            tryEvaluateExpression()
        }
    }

    fun setBackspaceButton() {
        val backspaceButton = findViewById<Button>(R.id.backSpaceButton)
        backspaceButton.setOnClickListener {
            val previousText = expressionField.text?.toString() ?: ""
            if (previousText.isNotEmpty()) {
                expressionField.text = previousText.substring(0, previousText.length - 1)
            }
        }
    }

    fun tryEvaluateExpression() {
        val expression = expressionField.text.toString()
        try {
            val result = InfixEvaluator.evaluate(expression).toString()
            expressionField.text = result
        } catch (e: ExpressionEvaluabilityException) {
            e.message?.let { showError(it) } ?: showError("Could not evaluate")
        } catch (e: ParsingException) {
            e.message?.let { showError(it) } ?: showError("Could not parse")
        }
    }


    private fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
