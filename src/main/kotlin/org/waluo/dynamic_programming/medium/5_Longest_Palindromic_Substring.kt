package org.waluo.dynamic_programming.medium

class Solution5 {
    fun longestPalindrome(s: String): String {
        val dp = Array(s.length) { BooleanArray(s.length) }
        return (s.indices).fold(0 to 1) { max, i ->
            dp[i][i] = true
            (0 until i).map { j ->
                if (s[i] == s[j] && (i - j < 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true
                    j to (i + 1 - j)
                } else {
                    max
                }
            }.maxBy { it.second } ?: max
        }.let { s.drop(it.first).take(it.second) }
    }
}