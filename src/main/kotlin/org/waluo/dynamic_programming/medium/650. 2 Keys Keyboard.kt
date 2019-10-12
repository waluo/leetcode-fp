package org.waluo.dynamic_programming.medium

class Solution650 {
    fun minSteps(n: Int): Int {
        return (2..n).fold(IntArray(n + 1)) { dp, i ->
            val j = (i - 1 downTo 1).first { i % it == 0 }
            dp[i] = dp[j] + i / j
            dp
        }[n]
    }
}