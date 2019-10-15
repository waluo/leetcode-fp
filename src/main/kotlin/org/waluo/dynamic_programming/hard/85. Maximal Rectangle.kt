package org.waluo.dynamic_programming.hard

import org.junit.jupiter.api.Test

class Solution85 {
    fun maximalRectangle(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) return 0
        val n = matrix.size
        val m = matrix.first().size
        val left = IntArray(m) { 0 }
        val right = IntArray(m) { m }
        val height = IntArray(m) { 0 }
        return (0 until n).fold(0) { max, i ->
            (0 until m).forEach { j ->
                if (matrix[i][j] == '1') height[j]++ else height[j] = 0
            }
            (0 until m).fold(0) { acc, j ->
                if (matrix[i][j] == '1') {
                    left[j] = maxOf(left[j], acc)
                    acc
                } else {
                    left[j] = 0
                    j + 1
                }
            }
            (m - 1 downTo 0).fold(m) { acc, j ->
                if (matrix[i][j] == '1') {
                    right[j] = minOf(right[j], acc)
                    acc
                } else {
                    right[j] = m
                    j
                }
            }
            (0 until m).fold(max) { acc, j ->
                ((right[j] - left[j]) * height[j]).coerceAtLeast(acc)
            }.coerceAtLeast(max)
        }
    }

    @Test
    fun test() {
        println(
            maximalRectangle(
                arrayOf(
                    charArrayOf('1', '0', '1', '0', '0'),
                    charArrayOf('1', '0', '1', '1', '1'),
                    charArrayOf('1', '1', '1', '1', '1'),
                    charArrayOf('1', '0', '0', '1', '0')
                )
            )
        )
    }
}