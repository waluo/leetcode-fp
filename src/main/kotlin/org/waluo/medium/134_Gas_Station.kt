package org.waluo.medium

class Solution134 {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        val size = gas.size
        return gas.indices.firstOrNull { index ->
            (0 until size).map { (it + index) % size }.fold(0) { acc, i ->
                if (acc < 0) acc else acc + gas[i] - cost[i]
            } >= 0
        } ?: -1
    }
}