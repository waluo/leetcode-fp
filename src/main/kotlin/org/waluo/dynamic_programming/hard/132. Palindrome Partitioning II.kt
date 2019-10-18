package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test

class Solution132 {
    fun minCut(s: String): Int {
        val dp = Array(s.length) { BooleanArray(s.length) }
        val dpCut = IntArray(s.length)
        s.indices.forEach { j ->
            dpCut[j] = j
            (0..j).forEach { i ->
                if (s[i] == s[j] && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true
                    dpCut[j] = if (i == 0) 0 else minOf(dpCut[j], dpCut[i - 1] + 1)
                }
            }
        }
        return dpCut.last()
    }

    @Test
    fun test(){
        println(minCut("aab"))
    }
}