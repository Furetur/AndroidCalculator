package com.example.calculator.expressions.operands

import com.example.calculator.expressions.ExpressionElement

interface Operand<Num : Number> : ExpressionElement {
    val value: Num
}
