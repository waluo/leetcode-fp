package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test

class Solution87 {
    fun isScramble(s1: String, s2: String): Boolean {
        val n = s1.length
        if (n != s2.length) return false
        if (s1 == s2) return true
        val dp = Array(n) { Array(n) { BooleanArray(n + 1) } }
        for (i in (0 until n)) {
            for (j in (0 until n)) {
                dp[i][j][1] = s1[i] == s2[j]
            }
        }
        for (len in (2..n)) {
            for (i in (0..n - len)) {
                for (j in (0..n - len)) {
                    dp[i][j][len] = (1 until len).any { k ->
                        dp[i][j][k] && dp[i + k][j + k][len - k] ||
                                dp[i + k][j][len - k] && dp[i][j + len - k][k]
                    }
                }
            }
        }
        return dp[0][0][n]
    }

    @Test
    fun test() {
        isScramble("abc", "acb")
    }
}