package org.waluo.dynamic_programming.hard

class Solution213 {
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        val dp0 = IntArray(nums.size)
        val dp1 = IntArray(nums.size)
        nums.indices.forEach { i ->
            dp0[i] = when {
                i < 2 -> nums[i]
                i == nums.size - 1 -> maxOf(dp0[i - 1], dp0[i - 2])
                i == 2 -> nums[i] + nums[i - 2]
                else -> maxOf(dp0[i - 2], dp0[i - 3]) + nums[i]
            }
            dp1[i] = when {
                i == 0 -> 0
                i < 3 -> nums[i]
                i == 3 -> nums[i] + nums[i - 2]
                else -> maxOf(dp1[i - 2], dp1[i - 3]) + nums[i]
            }
        }
        return maxOf(
            dp0.takeLast(2).max() ?: 0,
            dp1.takeLast(2).max() ?: 0
        )
    }

}