package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test

class Solution32 {
    fun longestValidParentheses(s: String): Int {
        val same = ")$s"
        val dp = IntArray(same.length)
        return (1 until same.length).maxBy { i ->
            if (same[i] == ')' && same[i - 1 - dp[i - 1]] == '(') {
                dp[i] = dp[i - 1] + 2
                dp[i] += dp[i - dp[i]]
            }
            dp[i]
        }?.let { dp[it] } ?: 0
    }

    fun other(s: String): Int {
        val stack: MutableList<Int> = ArrayList(s.length)
        s.indices.forEach { i ->
            if (s[i] == ')' && stack.lastOrNull()?.let { s[it] } == '(') {
                stack.removeAt(stack.size - 1)
            } else {
                stack.add(i)
            }
        }
        stack.add(0, -1)
        stack.add(s.length)
        return stack.zipWithNext().map { it.second - it.first - 1 }.max() ?: 0
    }

    @Test
    fun test() {
        println(longestValidParentheses(")()())"))
    }
}