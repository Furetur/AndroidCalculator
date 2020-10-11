package com.example.calculator.expressions.operands

import com.example.calculator.expressions.parsing.ParsingException
import java.math.BigDecimal

class DecimalOperand(override val value: BigDecimal) : Operand<BigDecimal> {
    companion object {
        fun parse(string: String) = DecimalOperand(parseDouble(string))

        private fun parseDouble(string: String) =
            string.toBigDecimalOrNull() ?: throw DoubleParsingException(string)
    }

    override fun toString(): String = value.toPlainString()

    class DoubleParsingException(invalidValue: String) :
        ParsingException("$invalidValue cannot be parsed as Double")
}
