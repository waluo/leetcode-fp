package org.waluo.medium

class Solution74 {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        return if (matrix.isEmpty() || matrix[0].isEmpty())
            false
        else matrix.lastOrNull { it[0] <= target }
            ?.binarySearch(target)
            ?.let { it >= 0 } ?: false
    }
}