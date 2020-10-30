package com.example.calculator.expressions

import com.example.calculator.expressions.operands.Operand
import com.example.calculator.expressions.operators.Operator
import com.example.calculator.expressions.parsing.LeftBrace
import com.example.calculator.expressions.parsing.ParsingException
import com.example.calculator.expressions.parsing.RightBrace
import java.util.Stack

object InfixToPostfixConverter {

    fun convert(rawExpression: List<ExpressionElement>) = sequence<ExpressionElement> {
        checkParenthesis(rawExpression)
        val stack = Stack<ExpressionElement>()

        for (element in rawExpression) {
            when (element) {
                is Operator -> {
                    while (stack.isNotEmpty()) {
                        val stackElement = stack.peek()
                        if (stackElement is Operator
                            && element.precedence <= stackElement.precedence
                        ) {
                            yield(stack.pop())
                        } else {
                            break
                        }
                    }
                    stack.add(element)
                }
                is RightBrace -> {
                    while (stack.isNotEmpty()) {
                        val currentElement = stack.pop()
                        if (currentElement is LeftBrace) {
                            break
                        } else {
                            yield(currentElement)
                        }
                    }
                }
                is LeftBrace -> stack.add(element)
                is Operand<*> -> yield(element)
            }
        }
        while (stack.isNotEmpty()) {
            yield(stack.pop())
        }
    }

    private fun checkParenthesis(rawExpression: List<ExpressionElement>) {
        var counter = 0
        for (element in rawExpression) {
            when (element) {
                is LeftBrace -> counter += 1
                is RightBrace -> {
                    counter -= 1
                    if (counter < 0) {
                        throw UnbalancedParenthesisException()
                    }
                }
            }
        }
        if (counter != 0) {
            throw UnbalancedParenthesisException()
        }
    }

    class UnbalancedParenthesisException : ParsingException("Check parenthesis")
}
