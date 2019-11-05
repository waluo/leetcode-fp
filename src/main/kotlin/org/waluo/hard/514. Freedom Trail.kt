package org.waluo.hard

import org.junit.jupiter.api.Test
import kotlin.math.absoluteValue

class Solution514 {
    fun findRotateSteps(ring: String, key: String): Int {
        val n = ring.length
        val m = key.length
        val dp = Array(m + 1) { IntArray(n) }
        for (i in m - 1 downTo 0) {
            for (j in ring.indices) {
                dp[i][j] = Int.MAX_VALUE
                for (k in ring.indices) {
                    if (ring[k] == key[i]) {
                        val diff = (j - k).absoluteValue
                        val step = minOf(diff, n - diff)
                        dp[i][j] = dp[i][j].coerceAtMost(
                            dp[i + 1][k] + step)
                    }
                }
            }
        }
        return dp[0][0] + m
    }

    @Test
    fun test() {
        println(findRotateSteps("godding", "godding"))
    }
}

