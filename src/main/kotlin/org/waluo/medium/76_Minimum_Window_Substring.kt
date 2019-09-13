package org.waluo.medium

import org.junit.jupiter.api.Test

class Solution76 {
    fun minWindow(s: String, t: String): String {
        val map = t.groupingBy { it }.eachCountTo(mutableMapOf())

        var count: Int = t.length
        val subs: MutableList<String> = mutableListOf()

        (s.indices).filter { map.contains(s[it]) }.fold(0) { acc, index ->
            val c = s[index]
            if (map[c]!! > 0) count -= 1
            map[c] = map[c]!! - 1
            var step = 0
            sequence {
                while (count == 0) yield(acc + step++)
            }.filter { map.contains(s[it]) }.forEach { delIndex ->
                val del = s[delIndex]
                if (map[del] == 0) {
                    count += 1
                    val element = s.substring(delIndex, index + 1)
                    subs.add(element)
                }
                map[del] = map[del]!! + 1
            }
            acc + step
        }

        return subs.minBy { it.length } ?: ""
    }

    @Test
    fun test() {
        println(minWindow("ADOBECODEBANC", "ABC"))
    }
}