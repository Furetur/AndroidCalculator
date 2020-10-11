package com.example.calculator.expressions.operators

import com.example.calculator.expressions.ExpressionEvaluabilityException
import com.example.calculator.expressions.operands.DecimalOperand
import com.example.calculator.expressions.operands.IntOperand
import com.example.calculator.expressions.operands.Operand
import java.lang.ArithmeticException
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

object DivisionOperator : DecimalOperator() {
    override val symbol = '/'

    override val precedence = OperatorPrecedence.PRODUCT

    private const val PRECISION = 6

    override fun operateWithDecimals(value1: BigDecimal, value2: BigDecimal) = try {
        val result = value1.divide(value2, PRECISION, RoundingMode.HALF_UP)
        DecimalOperand(result)
    } catch (e: ArithmeticException) {
        throw DivisionByZeroException()
    }


    class DivisionByZeroException : ExpressionEvaluabilityException("Cannot divide by 0")
}
