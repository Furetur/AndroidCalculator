package com.example.calculator.expressions.parsing

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ExpressionParserTest {
    private fun testOnExpression(parsedExpression: InfixExpressionGenerator.ParsedExpression) {
        val actual = ExpressionParser.parse(parsedExpression.expression).toList()
        assertEquals(parsedExpression.parsed, actual)
    }

    private fun testOnSimpleSum(size: Int, nested: Boolean = false) {
        val expression = if (nested) {
            InfixExpressionGenerator.makeSimpleSum(size)
        } else {
            InfixExpressionGenerator.makeNestedSimpleSum(size)
        }
        testOnExpression(expression)
    }

    private fun testOnDiverseExpression(size: Int, nested: Boolean = false) {
        val expression = if (nested) {
            InfixExpressionGenerator.makeDiverseExpression(size)
        } else {
            InfixExpressionGenerator.makeNestedDiverseExpression(size)
        }
        testOnExpression(expression)
    }

    private fun testOnMinusesExpression(size: Int) {
        val expression = InfixExpressionGenerator.makeMinusesExpression(size)
        testOnExpression(expression)
    }

    private fun testOnBigInteger(size: Int) {
        val expression = InfixExpressionGenerator.makeBigInteger(size)
        testOnExpression(expression)
    }

    private fun testOnBigDecimal(size: Int) {
        val expression = InfixExpressionGenerator.makeBigDecimal(size)
        testOnExpression(expression)
    }

    @Test
    fun `should throw ParsingError if encounters ++`() {
        val expression = InfixExpressionGenerator.makeDiverseExpression(100).expression + "++"
        assertThrows(ParsingException::class.java) {
            ExpressionParser.parse(expression).toList()
        }
    }

    @Test
    fun `should throw ParsingError if encounters any 2 consecutive operators`() {
        val expression = InfixExpressionGenerator.makeDiverseExpression(100).expression + "-*"
        assertThrows(ParsingException::class.java) {
            ExpressionParser.parse(expression).toList()
        }
    }

    @Test
    fun `should throw if encounters empty parenthesis`() {
        val expression = InfixExpressionGenerator.makeComplexExpressionWithMiddleTerm(100, "()")
        assertThrows(ParsingException::class.java) {
            ExpressionParser.parse(expression).toList()
        }
    }

    @Test
    fun `should throw if encounters unsupported symbol`() {
        val expression = InfixExpressionGenerator.makeComplexExpressionWithMiddleTerm(100, "S")
        assertThrows(ParsingException::class.java) {
            ExpressionParser.parse(expression).toList()
        }
    }


    @Test
    fun `should parse small simple sum`() {
        testOnSimpleSum(10)
    }

    @Test
    fun `should parse medium simple sum`() {
        testOnSimpleSum(50)
    }

    @Test
    fun `should parse huge simple sum`() {
        testOnSimpleSum(100)
    }

    @Test
    fun `should parse small diverse expression`() {
        testOnDiverseExpression(10)
    }

    @Test
    fun `should parse medium diverse expression`() {
        testOnDiverseExpression(50)
    }

    @Test
    fun `should parse huge diverse expression`() {
        testOnDiverseExpression(100)
    }

    @Test
    fun `should parse nested small simple sum`() {
        testOnSimpleSum(10, true)
    }

    @Test
    fun `should parse nested medium simple sum`() {
        testOnSimpleSum(50, true)
    }

    @Test
    fun `should parse nested huge simple sum`() {
        testOnSimpleSum(100, true)
    }

    @Test
    fun `should parse small nested diverse expression`() {
        testOnDiverseExpression(10, true)
    }

    @Test
    fun `should parse medium nested diverse expression`() {
        testOnDiverseExpression(50, true)
    }

    @Test
    fun `should parse huge nested diverse expression`() {
        testOnDiverseExpression(100, true)
    }

    @Test
    fun `should add zeros while parsing small minuses expression`() {
        testOnMinusesExpression(10)
    }

    @Test
    fun `should add zeros while parsing medium minuses expression`() {
        testOnMinusesExpression(50)
    }

    @Test
    fun `should add zeros while parsing huge minuses expression`() {
        testOnMinusesExpression(100)
    }

    @Test
    fun `should parse big integer`() {
        testOnBigInteger(10)
    }

    @Test
    fun `should parse really big integer`() {
        testOnBigInteger(50)
    }

    @Test
    fun `should parse big decimal`() {
        testOnBigDecimal(10)
    }

    @Test
    fun `should parse really big decimal`() {
        testOnBigDecimal(50)
    }
}
