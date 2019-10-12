package org.waluo.hard

fun maximumGap(nums: IntArray): Int {
    if (nums.size < 2)
        return 0
    val max = nums.max() ?: return 0
    val min = nums.min() ?: return 0
    val count = (max - min) / nums.size + 1
    val size = (max - min) / count + 1
    val bucket = nums.groupBy { it / count }
    return (0..size).mapNotNull { bucket[it] }.fold(0 to Int.MAX_VALUE) { acc, list ->
        maxOf(acc.first, (list.min()!! - acc.second).coerceAtLeast(0)) to list.max()!!
    }.first
}