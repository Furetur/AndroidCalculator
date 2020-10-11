package com.example.calculator.expressions.operators

import com.example.calculator.expressions.operands.DecimalOperand
import com.example.calculator.expressions.operands.IntOperand
import com.example.calculator.expressions.operands.Operand
import java.math.BigDecimal
import java.math.BigInteger

abstract class IntDecimalOperator : Operator() {
    override fun operate(operand1: Operand<*>, operand2: Operand<*>): Operand<*> {

        if (operand1 is IntOperand && operand2 is IntOperand) {
            return operateWithInts(operand1.value, operand2.value)
        }
        val value1 = when (operand1) {
            is IntOperand -> operand1.value.toBigDecimal()
            is DecimalOperand -> operand1.value
            else -> throw IllegalOperandsException(this, operand1, operand2)
        }
        val value2 = when (operand2) {
            is IntOperand -> operand2.value.toBigDecimal()
            is DecimalOperand -> operand2.value
            else -> throw IllegalOperandsException(this, operand1, operand2)
        }
        return operateWithDecimals(value1, value2)
    }

    abstract fun operateWithInts(value1: BigInteger, value2: BigInteger): Operand<*>

    abstract fun operateWithDecimals(value1: BigDecimal, value2: BigDecimal): Operand<*>
}
