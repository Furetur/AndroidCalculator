package com.example.calculator.expressions.operators

import com.example.calculator.expressions.operands.IntOperand
import com.example.calculator.expressions.operands.OperandGenerator
import org.junit.jupiter.api.Test

internal abstract class WholeIntOperatorTest : OperatorTest() {

    private val intOperand1 = OperandGenerator.makeBigIntOperand(10)
    private val intOperand2 = OperandGenerator.makeOperand(3)
    private val intOperand3 = OperandGenerator.makeBigIntOperand(15)

    @Test
    fun `result of 2 ints should be an int`() {
        val result = operator.operate(intOperand1, intOperand2)
        assert(result is IntOperand)
    }

    @Test
    fun `result of 2 big ints should be an int`() {
        val result = operator.operate(intOperand1, intOperand3)
        assert(result is IntOperand)
    }
}
