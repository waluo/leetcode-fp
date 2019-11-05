package org.waluo.hard

class Solution312 {
    fun maxCoins(nums: IntArray): Int {
        //dp[L][R]->maximum sum when any element between L and R deleted last
        val n = nums.size
        if (n == 0) return 0
        val dp = Array(n) {
            IntArray(n) { 1 }
        }
        for (l in n - 1 downTo 0) {
            for (r in l until n) {
                for (i in l..r) {
                    val left = if (l == 0) 1 else nums[l - 1]
                    val right = if (r == n - 1) 1 else nums[r + 1]
                    val leftVal = if (i == l) 0 else dp[l][i - 1]
                    val rightVal = if (i == r) 0 else dp[i + 1][r]
                    dp[l][r] = maxOf(
                        dp[l][r],
                        left * nums[i] * right + leftVal + rightVal
                    )
                }
            }
        }
        return dp[0][n - 1]
    }
}