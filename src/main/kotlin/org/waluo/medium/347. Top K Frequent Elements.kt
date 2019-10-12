package org.waluo.medium

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Solution347 {
    fun topKFrequent(nums: IntArray, k: Int): List<Int> {
        val map = nums
            .groupBy { it }
            .mapValues { it.value.size }
            .toList()
            .groupBy { it.second }

        return (1..nums.size).reversed()
            .flatMap { map.getOrDefault(it, emptyList()) }
            .take(k).map { it.first }
    }

    @Test
    fun test() {
        assertEquals(topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2), listOf(1, 2))
    }
}