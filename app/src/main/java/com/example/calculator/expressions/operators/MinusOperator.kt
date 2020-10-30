package com.example.calculator.expressions.operators

import com.example.calculator.expressions.operands.DecimalOperand
import com.example.calculator.expressions.operands.IntOperand
import java.math.BigDecimal
import java.math.BigInteger

object MinusOperator : IntDecimalOperator() {
    override val symbol = '-'

    override val precedence = OperatorPrecedence.ADDITION

    override fun operateWithInts(value1: BigInteger, value2: BigInteger) =
        IntOperand(value1 - value2)

    override fun operateWithDecimals(value1: BigDecimal, value2: BigDecimal) =
        DecimalOperand(value1 - value2)
}
