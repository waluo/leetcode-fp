package org.waluo.dynamic_programming.medium

import org.junit.jupiter.api.Test

class Solution787 {
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
        val inf = 10000000
        val srcMap = flights.groupBy { it[0] }
        val dp = Array(K + 1) { IntArray(n) { inf } }
        srcMap[src]?.forEach { dp[0][it[1]] = it[2] }
        (1..K).forEach { k ->
            flights.forEach { flight ->
                dp[k][flight[1]] = minOf(
                    dp[k][flight[1]],
                    dp[k - 1][flight[1]],
                    dp[k - 1][flight[0]] + flight[2]
                )
            }
        }
        return dp[K][dst].takeIf { it < inf } ?: -1
    }

    fun findCheapestPrice2(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
        val inf = 10000000
        val dist = Array(2) { IntArray(n) { inf } }

        dist[1][src] = 0
        dist[0][src] = dist[1][src]

        for (i in 0..K)
            for (edge in flights)
                dist[i and 1][edge[1]] =
                    Math.min(dist[i and 1][edge[1]], dist[i.inv() and 1][edge[0]] + edge[2])

        return if (dist[K and 1][dst] < inf) dist[K and 1][dst] else -1
    }

    @Test
    fun test() {
        findCheapestPrice(
            10,
            arrayOf(
                intArrayOf(3, 4, 4),
                intArrayOf(2, 5, 6),
                intArrayOf(4, 7, 10),
                intArrayOf(9, 6, 5),
                intArrayOf(7, 4, 4),
                intArrayOf(6, 2, 10),
                intArrayOf(6, 8, 6),
                intArrayOf(7, 9, 4),
                intArrayOf(1, 5, 4),
                intArrayOf(1, 0, 4),
                intArrayOf(9, 7, 3),
                intArrayOf(7, 0, 5),
                intArrayOf(6, 5, 8),
                intArrayOf(1, 7, 6),
                intArrayOf(4, 0, 9),
                intArrayOf(5, 9, 1),
                intArrayOf(8, 7, 3),
                intArrayOf(1, 2, 6),
                intArrayOf(4, 1, 5),
                intArrayOf(5, 2, 4),
                intArrayOf(1, 9, 1),
                intArrayOf(7, 8, 10),
                intArrayOf(0, 4, 2),
                intArrayOf(7, 2, 8)
            ),
            6,
            0,
            7
        ).let { println(it) }
    }
}

fun main() {
    (0..30).forEach { println(it and 1) }
}