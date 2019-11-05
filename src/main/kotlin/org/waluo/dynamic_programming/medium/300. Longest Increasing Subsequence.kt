package org.waluo.dynamic_programming.medium

class Solution300 {
    fun lengthOfLIS(nums: IntArray): Int {
        val dp = IntArray(nums.size) { 1 }
        (1 until nums.size).forEach { i ->
            val max = (0 until i)
                .filter { nums[i] > nums[it] }
                .map { dp[it] }.max() ?: 0
            dp[i] = max + 1
        }
        return dp.max() ?: 0
    }
}