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
        val memo = IntArray(nums.size) { -1 }
        var answer = 0
        for (i in nums.indices) {
            if (i == 0 || i == 1) memo[i] = nums[i]
            if (i == 2) memo[i] = nums[i] + memo[0]
            if (i > 2) {
                memo[i] = maxOf(nums[i] + memo[i - 2], nums[i] + memo[i - 3])
            }
            answer = maxOf(answer, memo[i])
        }
        return answer
    }
}