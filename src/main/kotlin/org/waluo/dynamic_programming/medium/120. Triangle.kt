package org.waluo.dynamic_programming.medium

import org.junit.jupiter.api.Test

class Solution120 {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val dp = Array(triangle.size) { IntArray(it + 1) }
        dp[0][0] = triangle[0][0]
        (1 until triangle.size).forEach { i ->
            triangle[i].indices.forEach { j ->
                val left = if (j - 1 > -1) dp[i - 1][j - 1] else Int.MAX_VALUE
                val right = if (j < dp[i - 1].size) dp[i - 1][j] else Int.MAX_VALUE
                dp[i][j] = minOf(left, right) + triangle[i][j]
            }
        }
        return dp.last().min() ?: 0
    }

    fun minimumTotal2(triangle: List<List<Int>>): Int {
        val dp = triangle.last().toIntArray()
        for (i in triangle.size - 2 downTo 0) {
            for (j in triangle[i].indices) {
                dp[j] = triangle[i][j] + minOf(dp[j], dp[j + 1])
            }
        }
        return dp.first()
    }

    @Test
    fun test() {
        println(minimumTotal(listOf(listOf(2), listOf(3, 4), listOf(6, 5, 7), listOf(4, 1, 8, 3))))
    }
}