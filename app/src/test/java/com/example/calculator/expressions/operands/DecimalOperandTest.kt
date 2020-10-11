package com.example.calculator.expressions.operands

import com.example.calculator.expressions.parsing.ParsingException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class DecimalOperandTest {

    @Test
    fun `should parse small value`() {
        val value = BigDecimal.valueOf(3.33)
        val string = value.toPlainString()
        val operand = DecimalOperand.parse(string)
        assertEquals(value, operand.value)
    }

    @Test
    fun `should throw ParsingException if big decimal contains multiple points`() {
        assertThrows(ParsingException::class.java) {
            DecimalOperand.parse("1.1.1")
        }
    }

    @Test
    fun `should generally throw ParsingException if input is not correct`() {
        assertThrows(ParsingException::class.java) {
            DecimalOperand.parse("asd")
        }
    }
}
