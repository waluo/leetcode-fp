package org.waluo.medium

class Solution221 {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) {
            return 0
        }
        val buffer = IntArray(matrix[0].size) { 0 }
        var max = 0
        matrix.forEach { row ->
            var last = 0
            for ((index, c) in row.withIndex()) {
                val tmp = buffer[index]
                when {
                    c == '0' -> buffer[index] = 0
                    index == 0 -> buffer[index] = 1
                    else -> buffer[index] = minOf(buffer[index - 1], buffer[index], last) + 1
                }
                max = maxOf(max, buffer[index])
                last = tmp
            }
        }
        return max * max
    }
}