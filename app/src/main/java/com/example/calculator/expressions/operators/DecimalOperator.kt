package com.example.calculator.expressions.operators

import java.math.BigInteger

abstract class DecimalOperator : IntDecimalOperator() {
    override fun operateWithInts(value1: BigInteger, value2: BigInteger) =
        operateWithDecimals(value1.toBigDecimal(), value2.toBigDecimal())
}
