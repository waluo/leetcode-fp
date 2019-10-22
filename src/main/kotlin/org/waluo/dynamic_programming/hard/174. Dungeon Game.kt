package org.waluo.dynamic_programming.hard


class Solution173 {
    fun calculateMinimumHP(dungeon: Array<IntArray>): Int {
        val m = dungeon.size
        val n = dungeon[0].size
        val minHp = Array(m) { IntArray(n) { Integer.MAX_VALUE } }
        minHp[m - 1][n - 1] = (1 - dungeon[m - 1][n - 1]).coerceAtLeast(1)
        for (i in dungeon.indices.reversed()) {
            for (j in dungeon[i].indices.reversed()) {
                if (i == m - 1 && j == n - 1) continue
                val fromRight = if (j + 1 < n) minHp[i][j + 1] else Integer.MAX_VALUE
                val fromBottom = if (i + 1 < m) minHp[i + 1][j] else Integer.MAX_VALUE
                val minHealth = minOf(fromRight, fromBottom) - dungeon[i][j]
                minHp[i][j] = minHealth.coerceAtLeast(1)
            }
        }
        return minHp[0][0]
    }
}
