package org.waluo.dynamic_programming.medium

import org.junit.jupiter.api.Test
import org.waluo.nonDivisibleSubset

class Solution787 {

    fun findCheapestPriceBFS(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
        val srcMap = flights.groupBy { it[0] }
        var min = Int.MAX_VALUE
        var queue = srcMap[src]?.map { 0 to it }?: emptyList()
        for (i in (0..K)) {
            queue.filter { it.second[1] == dst }
                .map { it.first + it.second[2] }.min()?.apply {
                min = minOf(min, this)
            }
            queue = queue.filter { it.second[1] != dst }
                .flatMap {last -> srcMap[last.second[1]]?.map { last.first + last.second[2] to it }?: emptyList() }
                .filter { it.first < min }
        }
        return min.takeIf { it < Int.MAX_VALUE } ?: -1
    }

    fun findCheapestPriceDFS(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
        val srcMap = flights.groupBy { it[0] }
        val visit = IntArray(n)
        fun findCheapestPrice(srcMap: Map<Int, List<IntArray>>, curr: Int, k: Int, maxPrice: Int): Int {
            if (curr == dst) return 0
            if (k == -1) return maxPrice
            return srcMap[curr]?.asSequence()
                ?.filter { visit[it[1]] == 0 }
                ?.fold(maxPrice) { acc, flight ->
                    if(flight[2] >= acc) return@fold acc
                    visit[flight[1]] = 1
                    val p = flight[2] + findCheapestPrice(srcMap, flight[1], k - 1, acc - flight[2])
                    visit[flight[1]] = 0
                    p
                } ?: maxPrice
        }
        return findCheapestPrice(srcMap, src, K, Int.MAX_VALUE).takeIf { it < Int.MAX_VALUE } ?: -1
    }

    fun findCheapestPriceDPWithPrintPath(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
        val inf = 1000000000
        val dp = Array(K + 1) { Array(n) { listOf(intArrayOf(-1, -1, inf)) } }
        flights.filter { it[0] == src }.forEach { dp[0][it[1]] = listOf(it) }
        dp[0][src] = listOf(intArrayOf(src, src, 0))
        (1..K).forEach { k ->
            dp[k][src] = listOf(intArrayOf(src, src, 0))
            flights.forEach { flight ->
                dp[k][flight[1]] = sequenceOf(
                    dp[k][flight[1]],
                    dp[k - 1][flight[0]] + flight
                )
                    .minBy { list -> list.sumBy { it[2] } }!!
            }
        }
        val cheapest = dp.last()[dst]
        cheapest.joinToString { "${it[0]} -> ${it[1]} cost ${it[2]}" }.apply { println(this) }
        return cheapest.sumBy { it[2] }.takeIf { it < inf } ?: 0
    }

    fun findCheapestPrice2(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
        val inf = 10000000
        val srcMap = flights.groupBy { it[0] }
        val dp = Array(K + 1) { IntArray(n) { inf } }
        srcMap[src]?.forEach { dp[0][it[1]] = it[2] }
        (1..K).forEach { k ->
            flights.forEach { flight ->
                dp[k][flight[1]] = minOf(
                    dp[k][flight[1]], dp[k - 1][flight[1]], dp[k - 1][flight[0]] + flight[2]
                )
            }
        }
        return dp[K][dst].takeIf { it < inf } ?: -1
    }

    @Test
    fun test() {
        findCheapestPriceBFS(
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