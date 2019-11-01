package org.waluo.dynamic_programming.medium

import org.junit.jupiter.api.Test

class Solution204 {
    fun nthUglyNumber(n: Int): Int {
        val dp = IntArray(n)
        val p = intArrayOf(0, 0, 0, 2, 3, 5)
        dp[0] = 1
        for (i in 1 until n) {
            dp[i] = (0..2).map { dp[p[it]] * p[it + 3] }.min() ?: 0
            (0..2).filter { dp[p[it]] * p[it + 3] == dp[i]}.forEach { p[it]++ }
        }
        return dp.last()
    }

    @Test
    fun test(){
        println(nthUglyNumber(10))
    }
}