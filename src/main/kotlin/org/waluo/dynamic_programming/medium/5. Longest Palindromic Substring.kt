package org.waluo.dynamic_programming.medium

import org.junit.jupiter.api.Test

class Solution5 {
    fun longestPalindrome(s: String): String {
        val dp = Array(s.length) { BooleanArray(s.length) }
        return (s.indices).fold(0 to 1) { max, j ->
            (0..j).map { i ->
                if (s[i] == s[j] && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true
                    i to (j + 1 - i)
                } else {
                    max
                }
            }.maxBy { it.second } ?: max
        }.let { s.drop(it.first).take(it.second) }
    }

    @Test
    fun test(){
        println(longestPalindrome("abccbajjjjab"))
    }
}