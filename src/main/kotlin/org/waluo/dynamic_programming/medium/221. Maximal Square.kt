package org.waluo.dynamic_programming.medium

import org.junit.jupiter.api.Test

class Solution221 {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) return 0
        val n = matrix.size
        val m = matrix.first().size
        val dp = Array(n) { IntArray(m) }
        return (0 until n).fold(0) { max, i ->
            (0 until m).fold(max) { acc, j ->
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j].toString().toInt()
                } else if (matrix[i][j] == '1') {
                    dp[i][j] = minOf(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
                }
                dp[i][j].coerceAtLeast(acc)
            }.coerceAtLeast(max)
        }.let { it * it }
    }

    @Test
    fun test() {
        maximalSquare(
            arrayOf(
                charArrayOf('1', '0', '1', '0', '0'),
                charArrayOf('1', '0', '1', '1', '1'),
                charArrayOf('1', '1', '1', '1', '1'),
                charArrayOf('1', '0', '0', '1', '0')
            )
        )
    }
}