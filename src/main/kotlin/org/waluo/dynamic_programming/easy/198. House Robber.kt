package org.waluo.dynamic_programming.easy

class Solution198 {
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var rob = nums[0]
        var skip = 0
        nums.drop(1).forEach { amount ->
            val temp = rob
            rob = maxOf(rob, skip + amount)
            skip = temp
        }
        return maxOf(rob, skip)
    }

    fun robDP(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val dp = IntArray(nums.size)
        nums.indices.forEach { i ->
            dp[i] = when {
                i < 2 -> nums[i]
                i == 2 -> nums[i] + nums[i - 2]
                else -> maxOf(dp[i - 2], dp[i - 3]) + nums[i]
            }
        }
        return dp.takeLast(2).max() ?: 0
    }
}