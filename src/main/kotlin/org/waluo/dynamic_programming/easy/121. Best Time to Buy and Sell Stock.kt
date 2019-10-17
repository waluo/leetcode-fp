package org.waluo.dynamic_programming.easy

class Solution121 {
    fun maxProfit(prices: IntArray): Int {
        var curr = prices.firstOrNull() ?: 0
        var max = 0
        prices.drop(1).forEach { n ->
            max = maxOf(max, n - curr)
            curr = minOf(curr, n)
        }
        return max
    }
}