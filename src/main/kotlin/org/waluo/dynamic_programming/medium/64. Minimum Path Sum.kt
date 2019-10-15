package org.waluo.dynamic_programming.medium

class Solution64 {
    fun minPathSum(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid.first().size
        val dp = Array(n) { IntArray(m) }
        dp[0][0] = grid[0][0]
        (1 until n).forEach { j -> dp[j][0] = dp[j - 1][0] + grid[j][0] }
        (1 until m).forEach { i -> dp[0][i] = dp[0][i - 1] + grid[0][i] }
        (1 until n).forEach { j ->
            (1 until m).forEach { i ->
                dp[j][i] = minOf(dp[j - 1][i], dp[j][i - 1]) + grid[j][i]
            }
        }
        return dp[n - 1][m - 1]
    }
}