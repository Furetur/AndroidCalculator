package com.example.calculator.expressions

import com.example.calculator.expressions.operands.OperandGenerator
import com.example.calculator.expressions.operators.PlusOperator
import com.example.calculator.expressions.operators.ProductOperator
import com.example.calculator.expressions.parsing.LeftBrace
import com.example.calculator.expressions.parsing.RightBrace
import java.math.BigInteger

object PostfixExpressionGenerator {
    data class PostfixInfixExpression(
        val postfix: List<ExpressionElement>,
        val infix: List<ExpressionElement>,
        val result: BigInteger,
    )

    fun makeUnbalancedInfixExpression(size: Int): List<ExpressionElement> {
        val expression = listOf(OperandGenerator.ONE)
        val nestedExpression = nest(size, expression).toMutableList()
        nestedExpression.add(RightBrace)
        return nestedExpression
    }

    private fun nest(size: Int, expression: List<ExpressionElement>): List<ExpressionElement> {
        val left = List(size) { LeftBrace }
        val right = List(size) { RightBrace }
        return left + expression + right
    }

    fun makeBinaryTreeLikeExpression(size: Int): PostfixInfixExpression {
        if (size == 1) {
            val first = OperandGenerator.makeOperand(1)
            val second = OperandGenerator.makeOperand(2)
            return PostfixInfixExpression(
                listOf(first, second, ProductOperator),
                listOf(LeftBrace, first, ProductOperator, second, RightBrace),
                2.toBigInteger()
            )
        }
        val first = makeBinaryTreeLikeExpression(size - 1)
        val second = makeBinaryTreeLikeExpression(size - 1)
        val finalPostfix = mutableListOf<ExpressionElement>()
        finalPostfix.addAll(first.postfix)
        finalPostfix.addAll(second.postfix)
        finalPostfix.add(PlusOperator)
        val finalInfix = mutableListOf<ExpressionElement>()
        finalInfix.add(LeftBrace)
        finalInfix.addAll(first.infix)
        finalInfix.add(PlusOperator)
        finalInfix.addAll(second.infix)
        finalInfix.add(RightBrace)
        return PostfixInfixExpression(finalPostfix, finalInfix, first.result + second.result)
    }

    fun makeSimpleSum(size: Int): PostfixInfixExpression {
        val infix = mutableListOf<ExpressionElement>()
        for (i in 0 until size) {
            infix.add(OperandGenerator.ONE)
            if (i != size - 1) {
                infix.add(PlusOperator)
            }
        }
        val postfix = listOf(infix.last()) + infix.subList(0, infix.size - 1)
        return PostfixInfixExpression(postfix, infix, size.toBigInteger())
    }
}
