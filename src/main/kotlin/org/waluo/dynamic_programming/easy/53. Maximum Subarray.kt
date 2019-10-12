package org.waluo.dynamic_programming.easy

class Solution53 {
    fun maxSubArray(nums: IntArray): Int {
        val dp = IntArray(nums.size + 1)
        nums.indices.forEach { i ->
            dp[i + 1] = nums[i] + dp[i].coerceAtLeast(0)
        }
        return dp.drop(1).max() ?: 0
    }
}