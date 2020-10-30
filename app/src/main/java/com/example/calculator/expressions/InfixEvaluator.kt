package com.example.calculator.expressions

import com.example.calculator.expressions.operands.Operand
import com.example.calculator.expressions.parsing.ExpressionParser

object InfixEvaluator {
    fun evaluate(infixExpression: String): Operand<*> {
        val parsedExpression = ExpressionParser.parse(infixExpression).toList()
        return evaluate(parsedExpression)
    }

    fun evaluate(infixExpression: List<ExpressionElement>): Operand<*> {
        val rawPostfixExpression = InfixToPostfixConverter.convert(infixExpression).toList()
        val postfixExpression = PostfixExpression(rawPostfixExpression)
        return postfixExpression.evaluate()
    }
}
