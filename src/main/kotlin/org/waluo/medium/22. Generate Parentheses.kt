package org.waluo.medium

import org.junit.jupiter.api.Test

class Solution22 {
    fun generateParenthesis(n: Int): List<String> {
        return (1 until n * 2).fold(listOf(1 to "(")) { acc, i ->
            acc.flatMap { (left, buffer) ->
                when {
                    left == n -> listOf(left to "$buffer)")
                    left > (i - left) -> listOf(left + 1 to "$buffer(", left to "$buffer)")
                    else -> listOf(left + 1 to "$buffer(")
                }
            }
        }.map { it.second }
    }

    fun generateParenthesis2(n: Int): List<String> {
        val ret = mutableListOf(1 to "(")
        for (i in (1 until n * 2)) {
            val temp: MutableList<Pair<Int, String>> = mutableListOf()
            for ((left, buffer) in ret) {
                when {
                    left == n -> temp += (left to "$buffer)")
                    left > (i - left) -> {
                        temp += (left to "$buffer)")
                        temp += (left + 1 to "$buffer(")
                    }
                    else -> temp += (left + 1 to "$buffer(")
                }
            }
            ret.clear()
            ret.addAll(temp)
        }
        return ret.map { it.second }
    }

    @Test
    fun test() {
        println(generateParenthesis2(3))
    }
}