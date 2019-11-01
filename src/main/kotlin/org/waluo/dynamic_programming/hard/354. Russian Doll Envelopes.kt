package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test

class Solution354 {
    fun maxEnvelopes(envelopes: Array<IntArray>): Int {
        val sort = envelopes.sortedWith(compareBy({ it[0] }, { it[1] }))
        val dp = IntArray(sort.size) { 1 }
        sort.indices.drop(1).forEach { i ->
            val j = (i - 1 downTo 0).filter {
                sort[i][0] > sort[it][0] && sort[i][1] > sort[it][1]
            }.maxBy { dp[it] }
            dp[i] = j?.let { dp[it] + 1 } ?: 1
        }
        return dp.lastOrNull() ?: 0
    }

    @Test
    fun test() {
        println(maxEnvelopes(arrayOf(intArrayOf(5, 4), intArrayOf(6, 4), intArrayOf(6, 7), intArrayOf(2, 3))))
    }
}