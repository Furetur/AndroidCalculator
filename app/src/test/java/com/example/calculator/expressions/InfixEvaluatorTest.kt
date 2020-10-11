package com.example.calculator.expressions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class InfixEvaluatorTest {
    private fun testOnComputedExpression(expression: ComputedExpressionGenerator.ComputedExpression) {
        val actual = InfixEvaluator.evaluate(expression.expression)
        assertEquals(expression.result, actual.value)
    }

    private fun testOnSimpleSum(size: Int) {
        val expression = ComputedExpressionGenerator.makeSimpleSum(size)
        testOnComputedExpression(expression)
    }

    private fun testOnNestedSimpleSum(size: Int) {
        val expression = ComputedExpressionGenerator.makeNestedSum(size)
        testOnComputedExpression(expression)
    }

    private fun testOnSubtractionBrackets(size: Int) {
        val expression = ComputedExpressionGenerator.makeSubtractionBracketsTest(size)
        testOnComputedExpression(expression)
    }

    @Test
    fun `should calculate simple sum`() {
        testOnSimpleSum(10)
    }

    @Test
    fun `should calculate huge simple sum`() {
        testOnSimpleSum(1000)
    }

    @Test
    fun `should handle nested sum`() {
        testOnNestedSimpleSum(10)
    }

    @Test
    fun `should handle huge nested sum`() {
        testOnNestedSimpleSum(1000)
    }

    @Test
    fun `should calculate sequence of minuses`() {
        testOnSubtractionBrackets(5)
    }

    @Test
    fun `should calculate huge sequence of minuses`() {
        testOnSubtractionBrackets(1000)
    }
}
