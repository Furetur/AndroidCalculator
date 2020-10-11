package com.example.calculator.expressions.operands

import com.example.calculator.expressions.parsing.ParsingException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class IntOperandTest {
    @Test
    fun `should throw ParsingException if big int contains points`() {
        assertThrows(ParsingException::class.java) {
            IntOperand.parse("1.1")
        }
    }

    @Test
    fun `should generally throw ParsingException if input is not correct`() {
        assertThrows(ParsingException::class.java) {
            IntOperand.parse("asd")
        }
    }
}
