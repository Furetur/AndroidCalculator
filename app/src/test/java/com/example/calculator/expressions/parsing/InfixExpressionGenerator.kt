package com.example.calculator.expressions.parsing

import com.example.calculator.expressions.ExpressionElement
import com.example.calculator.expressions.ComputedExpressionGenerator
import com.example.calculator.expressions.operands.IntOperand
import com.example.calculator.expressions.operands.OperandGenerator
import com.example.calculator.expressions.operators.*
import java.util.*

object InfixExpressionGenerator {
    class ParsedExpression(val expression: String, val parsed: List<ExpressionElement>)

    private val operators = listOf(
        PlusOperator,
        MinusOperator,
        ProductOperator,
        DivisionOperator
    )

    fun makeBigDecimal(size: Int): ParsedExpression {
        val operand = OperandGenerator.makeBigDecimalOperand(size)
        val expression = operand.value.toPlainString()
        return ParsedExpression(expression, listOf(operand))
    }

    fun makeBigInteger(size: Int): ParsedExpression {
        val operand = OperandGenerator.makeBigIntOperand(size)
        val expression = operand.value.toString()
        return ParsedExpression(expression, listOf(operand))
    }

    fun makeMinusesExpression(size: Int): ParsedExpression {
        val elements = LinkedList(listOf(OperandGenerator.ZERO, MinusOperator, OperandGenerator.ONE))
        var expression = "-1"

        for (i in 0 until size) {
            expression = "-($expression)"
            elements.addFirst(LeftBrace)
            elements.addFirst(MinusOperator)
            elements.addFirst(OperandGenerator.ZERO)
            elements.addLast(RightBrace)
        }
        return ParsedExpression(expression, elements)
    }


    fun makeComplexExpressionWithMiddleTerm(size: Int, middleTerm: String): String {
        val expressionPart = makeNestedDiverseExpression(size).expression
        return "$expressionPart+$middleTerm+$expressionPart"
    }

    fun makeNestedSimpleSum(size: Int): ParsedExpression = nest(size, makeSimpleSum(size))

    fun makeNestedDiverseExpression(size: Int): ParsedExpression = nest(size, makeDiverseExpression(size))

    fun nest(size: Int, parsedExpression: ParsedExpression) = ParsedExpression(
        ComputedExpressionGenerator.nest(parsedExpression.expression, size),
        List(size) { LeftBrace } + parsedExpression.parsed + List(size) { RightBrace }
    )

    fun makeSimpleSum(size: Int) = makeSimpleExpression(size) { PlusOperator }

    fun makeDiverseExpression(size: Int) = makeSimpleExpression(size) { operators[it % 4] }

    private fun makeSimpleExpression(size: Int, makeOperator: (Int) -> Operator): ParsedExpression {
        val numbers = (0..size)
        var expression = ""
        val parsed = mutableListOf<ExpressionElement>()
        for ((index, number) in numbers.withIndex()) {
            val operand = IntOperand(number.toBigInteger())
            parsed.add(operand)
            expression += operand.value.toString()

            if (index != numbers.last) {
                val operator = makeOperator(number)
                parsed.add(operator)
                expression += operator.symbol
            }
        }
        return ParsedExpression(expression, parsed)
    }
}
