package org.waluo.dynamic_programming.medium

import org.junit.jupiter.api.Test

class Solution63 {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        if (obstacleGrid.isEmpty() || obstacleGrid.first().isEmpty()) {
            return 0
        }
        val n = obstacleGrid.size
        val m = obstacleGrid.first().size
        val dp = IntArray(n)
        (obstacleGrid.takeWhile { it[0] != 1 }.indices).forEach { i ->
            dp[i] = 1
        }
        for (j in 1 until m) {
            dp[0] = if (obstacleGrid[0][j] == 1) 0 else dp[0]
            for (i in 1 until n) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i] = 0
                } else {
                    dp[i] += dp[i - 1]
                }
            }
        }
        return dp.last()
    }

    @Test
    fun test() {
        println(uniquePathsWithObstacles(arrayOf(intArrayOf(0, 1))))
    }
}