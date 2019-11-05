package org.waluo.dynamic_programming.medium

class Solution334 {
    fun increasingTriplet(nums: IntArray): Boolean {
        val dp = IntArray(nums.size) { 1 }
        (1 until nums.size).forEach { i ->
            val max = (0 until i)
                .filter { nums[i] > nums[it] }
                .map { dp[it] }.max() ?: 0
            dp[i] = max + 1
            if(dp[i] > 2) return true
        }
        return false
    }
}