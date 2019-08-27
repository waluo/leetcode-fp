package org.waluo.medium

fun fourSumCount(A: IntArray, B: IntArray, C: IntArray, D: IntArray): Int {
    val map = C.asSequence().flatMap { c ->
        D.asSequence().map { d -> c + d }
    }.groupBy { it }.mapValues { it.value.size }
    return A.sumBy { a ->
        B.sumBy { b -> map.getOrDefault(-a - b, 0) }
    }
}