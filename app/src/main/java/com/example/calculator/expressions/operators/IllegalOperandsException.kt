package com.example.calculator.expressions.operators

import com.example.calculator.expressions.ExpressionEvaluabilityException
import com.example.calculator.expressions.operands.Operand

class IllegalOperandsException(operator: Operator, operand1: Operand<*>, operand2: Operand<*>) :
    ExpressionEvaluabilityException(
        "Operator ${operator.symbol} does not support operands ${operand1.value} ${operand2.value}"
    )
