package org.waluo.hard

import org.junit.jupiter.api.Test
import java.math.BigInteger

class Solution321 {
    fun maxNumber(nums1: IntArray, nums2: IntArray, k: Int): IntArray {
        val dp =
            Array(nums1.size + 1) {
                Array(nums2.size + 1) {
                    Array(k + 1) { BigInteger("0") }
                }
            }
        for (i in nums1.size downTo 0) {
            for (j in nums2.size downTo 0) {
                for (n in 1..k) {
                    dp[i][j][n] = when {
                        i == nums1.size && j == nums2.size -> BigInteger("0")
                        n > nums1.size - i + nums2.size - j -> BigInteger("0")
                        i == nums1.size -> maxOf(
                            BigInteger.TEN.pow(n - 1) * nums2[j].toBigInteger() +
                                    dp[i][j + 1][n - 1],
                            dp[i][j + 1][n]
                        )
                        j == nums2.size -> maxOf(
                            BigInteger.TEN.pow(n - 1) * nums1[i].toBigInteger() +
                                    dp[i + 1][j][n - 1],
                            dp[i + 1][j][n]
                        )
                        else ->
                            10.toBigInteger().pow(n - 1) * nums2[j].toBigInteger() + dp[i][j + 1][n - 1].max(
                            10.toBigInteger().pow(n - 1) * nums1[i].toBigInteger() + dp[i + 1][j][n - 1]
                        ).max(
                            dp[i][j + 1][n]
                        ).max(dp[i + 1][j][n])
                    }
                }
            }
        }
        return dp[0][0][k].toString().toCharArray().map {
            it.toString().toInt()
        }.toIntArray()
    }

    @Test
    fun test() {
//        Input:
//        nums1 = [3, 4, 6, 5]
//        nums2 = [9, 1, 2, 5, 8, 3]
//        k = 5
//        Output:
//        [9, 8, 6, 5, 3]
        maxNumber(intArrayOf(3, 4, 6, 5), intArrayOf(9, 1, 2, 5, 8, 3), 5).let { println(it.contentToString()) }
    }
}
