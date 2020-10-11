package com.example.calculator.expressions.operators

import com.example.calculator.expressions.ExpressionEvaluabilityException
import com.example.calculator.expressions.operands.DecimalOperand
import com.example.calculator.expressions.operands.IntOperand
import com.example.calculator.expressions.operands.OperandGenerator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger

internal class DivisionOperatorTest : OperatorTest() {

    override val operator = DivisionOperator

    val wholeOperand = OperandGenerator.makeOperand(10)
    val funnyDividend = OperandGenerator.makeOperand(3)

    val zeroOperand = IntOperand(BigInteger.ZERO)

    @Test
    fun `should throw if dividend is 0`() {
        assertThrows(ExpressionEvaluabilityException::class.java) {
            DivisionOperator.operate(wholeOperand, zeroOperand)
        }
    }

    @Test
    fun `should return DecimalOperand`() {
        val result = DivisionOperator.operate(wholeOperand, funnyDividend)
        assert(result is DecimalOperand)
    }

    @Test
    fun `should not omit floating point`() {
        val result = DivisionOperator.operate(wholeOperand, funnyDividend) as DecimalOperand
        assertNotEquals(result.value, result.value.toInt().toBigDecimal())
    }
}
