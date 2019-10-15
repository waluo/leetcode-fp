package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test

class Solution72 {
    fun minDistance(word1: String, word2: String): Int {
        val n = word1.length
        val m = word2.length
        val dp = Array(n + 1) { IntArray(m + 1) }
        (1..n).forEach { i -> dp[i][0] = i }
        (1..m).forEach { i -> dp[0][i] = i }
        (0 until n).forEach { i ->
            (0 until m).forEach { j ->
                if (word1[i] == word2[j]) {
                    dp[i + 1][j + 1] = dp[i][j]
                } else {
                    dp[i + 1][j + 1] = minOf(dp[i][j], dp[i + 1][j], dp[i][j + 1]) + 1
                }
            }
        }
        return dp[n][m]
    }

    @Test
    fun test(){
        println(minDistance("sea", "ate"))
    }
}