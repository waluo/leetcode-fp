package org.waluo.medium

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Solution96 {
    val map = mutableMapOf<Int, Int>()
    fun numTrees(n: Int): Int {
        return when (n) {
            0, 1 -> 1
            2 -> 2
            else -> map.getOrPut(n) {
                (1..n).sumBy { numTrees(it - 1) * numTrees(n - it) }
            }
        }
    }

    @Test
    fun test(){
        Assertions.assertEquals(numTrees(3), 5)
    }
}