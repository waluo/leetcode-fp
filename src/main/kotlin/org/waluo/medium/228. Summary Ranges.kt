package org.waluo.medium

fun summaryRanges(nums: IntArray): List<String> {
    return nums.fold(emptyList<Pair<Int, Int>>()) { acc, i ->
        when {
            acc.isEmpty() -> listOf(i to i)
            acc.last().second == i - 1 -> acc.dropLast(1) + (acc.last().first to i)
            else -> acc + (i to i)
        }
    }.map {
        when {
            it.first == it.second -> it.first.toString()
            else -> "${it.first}->${it.second}"
        }
    }
}