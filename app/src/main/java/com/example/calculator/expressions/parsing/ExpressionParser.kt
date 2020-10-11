package com.example.calculator.expressions.parsing

import com.example.calculator.expressions.ExpressionElement
import com.example.calculator.expressions.operands.DecimalOperand
import com.example.calculator.expressions.operands.IntOperand
import com.example.calculator.expressions.operators.PlusOperator
import com.example.calculator.expressions.operators.MinusOperator
import com.example.calculator.expressions.operators.ProductOperator
import com.example.calculator.expressions.operators.DivisionOperator
import java.math.BigInteger

object ExpressionParser {

    const val POINT = '.'

    val operatorSymbols = listOf(
        PlusOperator.symbol,
        MinusOperator.symbol,
        ProductOperator.symbol,
        DivisionOperator.symbol
    )

    val braceSymbols = listOf(LeftBrace.symbol, RightBrace.symbol)

    val infixOperatorSymbols = operatorSymbols + braceSymbols

    private val numerals = ('0'..'9') + '.'

    fun parse(string: String): Sequence<ExpressionElement> = sequence {
        verify(string)
        var currentNumber = ""
        for (symbol in string) {
            when (symbol) {
                in numerals -> currentNumber += symbol
                in infixOperatorSymbols -> {
                    if (currentNumber.isNotEmpty()) {
                        yield(parseOperand(currentNumber))
                        currentNumber = ""
                    } else if (symbol == MinusOperator.symbol) {
                        yield(IntOperand(BigInteger.ZERO))
                    }
                    yield(parseInfixOperator(symbol))
                }
                else -> throw UnsupportedSymbolException(symbol)
            }
        }
        if (currentNumber.isNotEmpty()) {
            yield(parseOperand(currentNumber))
        }
    }

    private fun verify(input: String) {
        for ((current, next) in input.zipWithNext()) {
            if (current in operatorSymbols && next in operatorSymbols) {
                throw ConsequentOperatorsException()
            }
            if (current == LeftBrace.symbol && next == RightBrace.symbol) {
                throw EmptyParenthesisException()
            }
        }
    }


    private fun parseOperand(serializedOperand: String) = if (serializedOperand.contains(POINT)) {
        DecimalOperand.parse(serializedOperand)
    } else {
        IntOperand.parse(serializedOperand)
    }

    private fun parseInfixOperator(serializedOperator: Char): ExpressionElement =
        when (serializedOperator) {
            PlusOperator.symbol -> PlusOperator
            MinusOperator.symbol -> MinusOperator
            ProductOperator.symbol -> ProductOperator
            DivisionOperator.symbol -> DivisionOperator
            LeftBrace.symbol -> LeftBrace
            RightBrace.symbol -> RightBrace
            else -> throw UnsupportedSymbolException(serializedOperator)
        }

    class ConsequentOperatorsException : ParsingException("Contains consequent operators")

    class EmptyParenthesisException : ParsingException("Contains empty parenthesis")

    class UnsupportedSymbolException(symbol: Char) : ParsingException("Unsupported symbol $symbol")
}
