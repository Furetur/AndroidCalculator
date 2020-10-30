package com.example.calculator.expressions

object ComputedExpressionGenerator {

    data class ComputedExpression(val expression: String, val result: Number)

    fun nest(string: String, size: Int) = "(".repeat(size) + string + ")".repeat(size)


    fun makeSubtractionBracketsTest(size: Int) = ComputedExpression(
        makeSubtractionBracketsExpression(size), ((size + 1) % 2).toBigInteger()
    )

    private tailrec fun makeSubtractionBracketsExpression(
        size: Int,
        accumulator: String = "1"
    ): String {
        if (size == 0) {
            return accumulator
        } else {
            return makeSubtractionBracketsExpression(size - 1, "(1-$accumulator)")
        }
    }

    fun makeSimpleSum(size: Int) =
        ComputedExpression(
            (1..size).joinToString("+"),
            ((1 + size).toBigInteger() * size.toBigInteger()) / 2.toBigInteger()
        )

    fun makeNestedSum(size: Int) = ComputedExpression(
        (1..size).joinToString("+") { nest(it.toString(), it) },
        ((1 + size).toBigInteger() * size.toBigInteger()) / 2.toBigInteger()
    )
}
