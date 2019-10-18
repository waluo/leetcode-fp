package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test

class Solution97 {
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if (s1.length + s2.length != s3.length) return false
        if (s3.isEmpty()) return true
        val dp = Array(s1.length + 1) { BooleanArray(s2.length + 1) }
        (0..s1.length).forEach { i1 ->
            (0..s2.length).forEach { i2 ->
                val use1 = i1 > 0 && s1[i1 - 1] == s3[i1 + i2 - 1] && dp[i1 - 1][i2]
                val use2 = i2 > 0 && s2[i2 - 1] == s3[i1 + i2 - 1] && dp[i1][i2 - 1]
                dp[i1][i2] = (i1 == 0 && i2 == 0) || use1 || use2
            }
        }
        return dp[s1.length][s2.length]
    }

    @Test
    fun test() {
        println(isInterleave("aabcc", "dbbca", "aadbbcbcac"))
    }
}
