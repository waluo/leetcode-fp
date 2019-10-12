package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test

class Solution44 {
    fun isMatch(s: String, p: String): Boolean {
        val dp = Array(p.length + 1) { mutableSetOf<Int>() }
        dp[0].add(-1)
        p.indices.forEach { i ->
            val next = when (p[i]) {
                '?' -> dp[i].map { it + 1 }.filter { it < s.length }
                '*' -> dp[i].min()?.let { it until s.length }?.toList() ?: emptyList()
                else -> dp[i].map { it + 1 }.filter { it < s.length }.filter { s[it] == p[i] }
            }
            dp[i + 1].addAll(next)
        }
        return dp.lastOrNull()?.contains(s.length - 1) ?: false
    }

    @Test
    fun test() {
        println(isMatch("adceb", "*a*b"))
    }
}