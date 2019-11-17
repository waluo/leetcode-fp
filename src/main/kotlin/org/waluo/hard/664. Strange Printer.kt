package org.waluo.hard

import org.junit.jupiter.api.Test

class Solution664 {
    fun strangePrinter(s: String): Int {
        val n = s.length
        val dp = Array(n) { IntArray(n) }
        for (l in n - 1 downTo 0)
            for (r in l until n) {
                dp[l][r] = if (l == r) 1 else dp[l + 1][r] + 1
                for (i in l + 1..r)
                    if (s[l] == s[i])
                        dp[l][r] = minOf(
                            dp[l][r], dp[l + 1][i - 1] + dp[i][r]
                        )
            }
        return if (n == 0) 0 else dp[0][n - 1]
    }

    @Test
    fun test() {
        println(strangePrinter("aaabbb"))
    }
}