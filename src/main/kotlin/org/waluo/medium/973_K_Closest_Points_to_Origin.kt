package org.waluo.medium

import kotlin.math.pow

class Solution973 {
    fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> {
        return points.asSequence()
            .sortedBy { it[0].toDouble().pow(2) + it[1].toDouble().pow(2) }
            .take(K).toList().toTypedArray()
    }
}