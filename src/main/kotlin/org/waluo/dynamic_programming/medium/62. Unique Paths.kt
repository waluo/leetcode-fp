package org.waluo.dynamic_programming.medium

class Solution62 {
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array(n) { IntArray(m) }
        (0 until m).forEach { dp[0][it] = 1 }
        (0 until n).forEach { dp[it][0] = 1 }
        (1 until n).forEach { j ->
            (1 until m).forEach { i ->
                dp[j][i] = dp[j - 1][i] + dp[j][i - 1]
            }
        }
        return dp[n - 1][m - 1]
    }
}