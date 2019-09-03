package org.waluo.medium

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Solution438 {
    fun findAnagrams(s: String, p: String): List<Int> {
        fun CharSequence.countMap() = groupBy { it }.mapValues { it.value.size }
        val map = p.countMap()
        return (0..s.length - p.length).filter { start: Int ->
            map.containsKey(s[start]) && s.subSequence(start, start + p.length).countMap() == map
        }
    }

    @Test
    fun test() {
        Assertions.assertEquals(findAnagrams("cbaebabacd", "abc"), listOf(0, 6))
    }
}