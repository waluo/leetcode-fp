package org.waluo.dynamic_programming.medium

class Solution91 {
    fun numDecodings(s: String): Int {
        val n = s.length
        if (n == 0) return 0

        val dp = IntArray(n + 1)
        dp[n] = 1
        dp[n - 1] = if (s[n - 1] != '0') 1 else 0

        for (i in n - 2 downTo 0)
            if (s[i] == '0')
                continue
            else
                dp[i] = if (s.substring(i, i + 2).toInt() <= 26) dp[i + 1] + dp[i + 2]
                else dp[i + 1]
        return dp[0]
    }
}