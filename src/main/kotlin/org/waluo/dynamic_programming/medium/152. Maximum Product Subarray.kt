package org.waluo.dynamic_programming.medium

import org.junit.jupiter.api.Test

class Solution152 {
    fun maxProduct(nums: IntArray): Int {
        val dp = Array(2) { IntArray(nums.size + 1) }
        dp[0][0] = 1
        dp[1][0] = 1
        var max = Int.MIN_VALUE
        (nums.indices).forEach { i ->
            if(nums[i] == 0){
                dp[0][i + 1] = 1
                dp[1][i + 1] = 1
                max = maxOf(0, max)
            }else{
                val dp0 = dp[0][i] * nums[i]
                val dp1 = dp[1][i] * nums[i]
                dp[0][i + 1] = maxOf(nums[i], dp0, dp1)
                dp[1][i + 1] = minOf(nums[i], dp0, dp1)
                max = maxOf(dp[0][i + 1], max)
            }
        }
        return max
    }

    @Test
    fun test() {
        println(maxProduct(intArrayOf(2, 3, -2, 4)))
    }
}