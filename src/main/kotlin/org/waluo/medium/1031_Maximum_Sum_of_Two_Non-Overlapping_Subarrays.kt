package org.waluo.medium

class Solution1031 {
    fun maxSumTwoNoOverlap(A: IntArray, L: Int, M: Int): Int {
        val sumL = A.indices.map { index ->
            A.drop(index).take(L).sum()
        }
        val sumM = A.indices.map { index ->
            A.drop(index).take(M).sum()
        }
        return A.indices.flatMap { index ->
            listOf(
                sumL[index] + (sumM.drop(index + L).max() ?: 0),
                sumM[index] + (sumL.drop(index + M).max() ?: 0)
            )
        }.max() ?: 0
    }
}

fun main() {
    println(Solution1031().maxSumTwoNoOverlap(intArrayOf(0,6,5,2,2,5,1,9,4), 1, 2))
}