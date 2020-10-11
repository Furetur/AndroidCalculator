package com.example.calculator.expressions.operands

import com.example.calculator.expressions.parsing.ParsingException
import java.math.BigInteger

class IntOperand(override val value: BigInteger) : Operand<BigInteger> {
    companion object {
        fun parse(string: String) = IntOperand(parseInt(string))

        private fun parseInt(string: String) =
            string.toBigIntegerOrNull() ?: throw IntParsingException(string)
    }

    override fun toString(): String = value.toString()

    class IntParsingException(invalidValue: String) :
        ParsingException("$invalidValue cannot be parsed as Int")
}
