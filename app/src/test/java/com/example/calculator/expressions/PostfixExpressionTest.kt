package com.example.calculator.expressions

import com.example.calculator.expressions.operands.OperandGenerator
import com.example.calculator.expressions.operators.MinusOperator
import com.example.calculator.expressions.operators.PlusOperator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.Exception

internal class PostfixExpressionTest {
    private fun testOnExpression(expression: PostfixExpressionGenerator.PostfixInfixExpression) {
        val actual = PostfixExpression(expression.postfix).evaluate().value
        assertEquals(expression.result, actual)
    }

    private fun testOnSimpleSum(size: Int) {
        val expression = PostfixExpressionGenerator.makeSimpleSum(size)
        testOnExpression(expression)
    }

    private fun testOnBinaryTreeLike(size: Int) {
        val expression = PostfixExpressionGenerator.makeBinaryTreeLikeExpression(size)
        testOnExpression(expression)
    }

    @Test
    fun `should throw if + has not enough operands`() {
        val expression = listOf(OperandGenerator.ONE, PlusOperator)
        assertThrows(ExpressionEvaluabilityException::class.java) {
            PostfixExpression(expression).evaluate()
        }
    }

    @Test
    fun `should throw if - has not enough operands`() {
        val expression = listOf(OperandGenerator.ONE, MinusOperator)
        assertThrows(ExpressionEvaluabilityException::class.java) {
            PostfixExpression(expression).evaluate()
        }
    }

    @Test
    fun `should evaluate simple sum correctly`() {
        testOnSimpleSum(10)
    }

    @Test
    fun `should evaluate huge simple sum correctly`() {
        testOnSimpleSum(1000)
    }

    @Test
    fun `should evaluate small binary tree expression correctly`() {
        testOnBinaryTreeLike(4)
    }

    @Test
    fun `should evaluate huge binary tree expression correctly`() {
        testOnBinaryTreeLike(10)
    }
}
