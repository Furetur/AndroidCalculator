package com.example.calculator.expressions.operators

import com.example.calculator.expressions.ExpressionElement
import com.example.calculator.expressions.operands.Operand

abstract class Operator : ExpressionElement {
    abstract val symbol: Char

    abstract val precedence: OperatorPrecedence

    abstract fun operate(operand1: Operand<*>, operand2: Operand<*>): Operand<*>

    override fun toString() = symbol.toString()
}
