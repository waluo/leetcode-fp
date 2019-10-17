package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test

class Solution123 {

    fun maxProfit(prices: IntArray): Int {
        var b0 = Int.MIN_VALUE
        var b1 = Int.MIN_VALUE
        var s0 = 0
        var s1 = 0
        prices.forEach {
            b0 = maxOf(b0, -it)
            s0 = maxOf(s0, b0 + it)
            b1 = maxOf(b1, s0 - it)
            s1 = maxOf(s1, b1 + it)
        }
        return s1
    }

    fun maxProfit2(prices: IntArray): Int {
        if (prices.size < 2) return 0
        val small = pre(prices)

        val dp = Array(small.size) { IntArray(small.size) }
        small.indices.forEach { i ->
            (i + 1 until small.size).forEach { j ->
                dp[i][j] = maxOf(small[j] - small[i], dp[i][j - 1])
            }
        }
        return small.indices.fold(0) { max, n ->
            val left = (0 until n).map { dp[it][n] }.max() ?: 0
            val right = (n + 1 until small.size).map { dp[it][small.size - 1] }.max() ?: 0
            maxOf(left + right, max)
        }
    }

    private fun pre(prices: IntArray): List<Int> {
        var index = 1
        var less = prices[0] > prices[1]
        (2 until prices.size).forEach { i ->
            if (less && prices[index] < prices[i]) {
                prices[++index] = prices[i]
                less = false
            } else if (!less && prices[index] > prices[i]) {
                prices[++index] = prices[i]
                less = true
            }
            prices[index] = prices[i]
        }
        return prices.take(index + 1)
    }

    @Test
    fun test() {
        println(maxProfit(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4)))
    }
}