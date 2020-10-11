package com.example.calculator.expressions

import com.example.calculator.expressions.operands.Operand
import com.example.calculator.expressions.operators.Operator
import java.util.Stack
import java.util.EmptyStackException

class PostfixExpression(private val rawExpression: List<ExpressionElement>) {
    companion object {
        fun verify(rawExpression: List<ExpressionElement>) {
            var counter = 0
            for (element in rawExpression) {
                when (element) {
                    is Operand<*> -> counter += 1
                    is Operator -> {
                        counter -= 2
                        if (counter < 0) {
                            throw NotEnoughOperandsException(element)
                        }
                        counter += 1
                    }
                }
            }
            if (counter != 1) {
                throw NotAnExpressionException()
            }
        }
    }

    init {
        verify(rawExpression)
    }

    fun evaluate(): Operand<*> {
        val stack = Stack<Operand<*>>()
        for (element in rawExpression) {
            when (element) {
                is Operand<*> -> stack.add(element)
                is Operator -> {
                    try {
                        val operand2 = stack.pop()
                        val operand1 = stack.pop()
                        val result = element.operate(operand1, operand2)
                        stack.add(result)
                    } catch (e: EmptyStackException) {
                        throw NotEnoughOperandsException(element)
                    }
                }
            }
        }
        return stack.pop() ?: throw NotAnExpressionException()
    }

    class NotAnExpressionException : ExpressionEvaluabilityException("Not an expression")

    class NotEnoughOperandsException(operator: Operator) :
        ExpressionEvaluabilityException("Operator ${operator.symbol} received less than 2 operands")
}
