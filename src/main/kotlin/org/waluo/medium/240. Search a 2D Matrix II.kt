package org.waluo.medium

class Solution240 {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if(matrix.isEmpty()){
            return false
        }
        val h = matrix.size - 1
        val w = matrix[0].size - 1
        var y = h
        var x = 0
        while (x <= w && y >= 0) {
            when {
                matrix[y][x] == target -> return true
                matrix[y][x] > target -> y--
                else -> x++
            }
        }
        return false
    }
}