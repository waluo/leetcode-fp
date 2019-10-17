package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test

class Solution115 {
    fun numDistinct(s: String, t: String): Int {
        val dp = Array(s.length + 1) { IntArray(t.length + 1) }
        (0..s.length).forEach { i -> dp[i][0] = 1 }
        (1..s.length).forEach { i ->
            (1..t.length).forEach { j ->
                dp[i][j] = dp[i - 1][j]
                if (s[i - 1] == t[j - 1]) {
                    dp[i][j] += dp[i - 1][j - 1]
                }
            }
        }
        return dp[s.length][t.length]
    }

    @Test
    fun test() {
        println(numDistinct("rabbbit", "rabbit"))
    }
}