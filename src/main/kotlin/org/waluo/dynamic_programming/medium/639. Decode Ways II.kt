package org.waluo.dynamic_programming.medium

class Solution639 {
    fun numDecodings(s: String): Int {
        val dp = LongArray(s.length + 1)
        dp[0] = 1
        dp[1] = (if (s[0] == '*') 9 else if (s[0] == '0') 0 else 1).toLong()
        for (i in 2..s.length) {
            val ch1 = s[i - 2]
            val ch2 = s[i - 1]
            if (ch2 == '*') {
                dp[i] = 9 * dp[i - 1]
            }
            if (ch2 in '1'..'9') {
                dp[i] = dp[i - 1]
            }
            if (ch1 == '*' && ch2 == '0') {
                dp[i] = 2 * dp[i - 2]
            }
            if (ch1 == '1' && ch2 == '*') {
                dp[i] += 9 * dp[i - 2]
            }
            if (ch1 == '2' && ch2 == '*') {
                dp[i] += 6 * dp[i - 2]
            }
            if (ch1 == '*' && (ch2 in '1'..'6')) {
                dp[i] += 2 * dp[i - 2]
            }
            if (ch1 == '*' && (ch2 in '7'..'9')) {
                dp[i] += dp[i - 2]
            }
            if (ch1 == '*' && ch2 == '*') {
                dp[i] += 15 * dp[i - 2]
            }
            if (ch2 >= '0' && (ch1 == '1' && ch2 <= '9' || ch1 == '2' && ch2 <= '6')
            ) {
                dp[i] += dp[i - 2]
            }
            dp[i] = dp[i] % 1000000007
        }
        return dp[s.length].toInt()
    }
}