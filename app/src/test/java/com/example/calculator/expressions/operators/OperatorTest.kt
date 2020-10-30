package com.example.calculator.expressions.operators

import com.example.calculator.expressions.operands.OperandGenerator
import com.example.calculator.expressions.operands.TestOperand
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

abstract class OperatorTest {

    abstract val operator: Operator

    @Test
    fun `should throw if receives unknown operand`() {
        val testOperand = TestOperand(1)
        val intOperand = OperandGenerator.makeOperand(5)
        Assertions.assertThrows(IllegalOperandsException::class.java) {
            operator.operate(testOperand, intOperand)
        }
    }
}
