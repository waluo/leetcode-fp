package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.collections.ArrayList

class Solution32 {
    fun longestValidParentheses(s: String): Int {
        val stack: MutableList<Pair<Char, Int>> = mutableListOf()
        s.indices.forEach {
            if (s[it] == ')' && stack.lastOrNull()?.first == '(') {
                stack.removeAt(stack.size - 1)
            } else {
                stack.add(s[it] to it)
            }
        }
        stack.add(0, 's' to -1)
        stack.add('e' to s.length)
        val list = stack.map { it.second }
        return list.zipWithNext().map { it.second - it.first - 1 }.max() ?: 0
    }

    @Test
    fun test() {
        println(longestValidParentheses(")()())"))
    }
}