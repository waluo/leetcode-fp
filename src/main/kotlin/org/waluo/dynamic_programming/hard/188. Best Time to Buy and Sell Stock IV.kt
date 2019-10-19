package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test

class Solution188 {
    fun maxProfit(k: Int, prices: IntArray): Int {
        if (k == 0 || prices.size < 2) return 0
        val dp = IntArray(2 * k.coerceAtMost(prices.size / 2)) {
            if (it % 2 == 0) Int.MIN_VALUE else 0
        }
        prices.forEach { price ->
            (dp.indices step 2).forEach { i ->
                val last = if (i == 0) 0 else dp[i - 1]
                dp[i] = maxOf(dp[i], last - price)
                dp[i + 1] = maxOf(dp[i + 1], dp[i] + price)
            }
        }
        return dp.last()
    }

    @Test
    fun test() {
        println(maxProfit(2, intArrayOf(2, 4, 1)))
    }
}