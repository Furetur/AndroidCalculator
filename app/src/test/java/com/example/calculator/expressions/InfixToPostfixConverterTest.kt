package com.example.calculator.expressions

import com.example.calculator.expressions.parsing.LeftBrace
import com.example.calculator.expressions.parsing.ParsingException
import com.example.calculator.expressions.parsing.RightBrace
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class InfixToPostfixConverterTest {

    private fun testOnExpression(expression: PostfixExpressionGenerator.PostfixInfixExpression) {
        val actual = InfixToPostfixConverter.convert(expression.infix).toList()
        assertEquals(expression.postfix, actual)
    }

    private fun testOnSimpleSum(size: Int) {
        val expression = PostfixExpressionGenerator.makeSimpleSum(size)
        testOnExpression(expression)
    }

    private fun testOnBinaryTreeLikeExpression(size: Int) {
        val expression = PostfixExpressionGenerator.makeBinaryTreeLikeExpression(size)
        testOnExpression(expression)
    }

    @Test
    fun `should throw on )(`() {
        assertThrows(ParsingException::class.java) {
            InfixToPostfixConverter.convert(listOf(RightBrace, LeftBrace)).toList()
        }
    }
    @Test
    fun `should throw on small deeply nested unbalances sequence`() {
        val expression = PostfixExpressionGenerator.makeUnbalancedInfixExpression(10)
        assertThrows(ParsingException::class.java) {
            InfixToPostfixConverter.convert(expression).toList()
        }
    }
    @Test
    fun `should throw on huge deeply nested unbalances sequence`() {
        val expression = PostfixExpressionGenerator.makeUnbalancedInfixExpression(100)
        assertThrows(ParsingException::class.java) {
            InfixToPostfixConverter.convert(expression).toList()
        }
    }

    @Test
    fun `should convert small simple sum`() {
        testOnSimpleSum(10)
    }

    @Test
    fun `should convert huge simple sum`() {
        testOnSimpleSum(1000)
    }

    @Test
    fun `should convert small binary tree expression`() {
        testOnBinaryTreeLikeExpression(4)
    }

    @Test
    fun `should convert huge binary tree expression`() {
        testOnBinaryTreeLikeExpression(10)
    }
}
