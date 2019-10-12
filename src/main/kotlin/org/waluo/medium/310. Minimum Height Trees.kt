package org.waluo.medium

class Solution310 {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        if (n == 1) return listOf(0)
        val map = edges.asSequence()
            .flatMap { sequenceOf(it[0] to it[1], it[1] to it[0]) }
            .groupBy { it.first }
            .mapValues { it.value.map(Pair<Int, Int>::second).toMutableList() }
            .toMutableMap()

        var left = n
        var leaf = map.filter { it.value.size == 1 }.keys
        while (leaf.size < left) {
            left -= leaf.size
            leaf = leaf.mapNotNull {
                val del = map[it]!!.first()
                map[del]!! -= it
                if (map[del]?.size == 1) del else null
            }.toSet()
        }
        return leaf.toList()
    }
}