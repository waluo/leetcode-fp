package org.waluo.dynamic_programming.easy

class Solution70 {
    fun climbStairs(n: Int): Int {
        if(n < 3) return n
        val dp = IntArray(n)
        dp[0] = 1
        dp[1] = 2
        (2 until n).forEach { i-> dp[i] = dp[i - 1] + dp[i - 2] }
        return dp.last()
    }
}