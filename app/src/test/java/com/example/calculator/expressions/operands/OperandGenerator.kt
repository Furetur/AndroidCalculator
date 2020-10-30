package com.example.calculator.expressions.operands

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

object OperandGenerator {
    val ZERO = makeOperand(0)
    val ONE = makeOperand(1)

    fun makeOperand(value: Int): IntOperand = IntOperand(value.toBigInteger())

    fun makeOperand(value: Double) = DecimalOperand(value.toBigDecimal())

    fun makeBigIntOperand(size: Int): IntOperand = IntOperand(BigInteger.TEN.pow(size))

    fun makeBigDecimalOperand(size: Int): DecimalOperand =
        DecimalOperand(
            BigDecimal.TEN.pow(size)
                .divide(BigDecimal.valueOf(3), 10, RoundingMode.HALF_UP)
        )
}
