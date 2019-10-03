package org.waluo.hard

import org.junit.jupiter.api.Test

class Solution4 {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val len1 = nums1.size
        val len2 = nums2.size
        if (len1 > len2) return findMedianSortedArrays(nums2, nums1)
        val k = (len1 + len2 + 1) / 2
        var l = 0
        var r = len1
        while (l < r) {
            val mid1 = (l + r) / 2
            val mid2 = k - mid1 - 1
            if (nums1[mid1] < nums2[mid2]) l = mid1 + 1
            else r = mid1
        }
        val c1 = maxOf(
            (l - 1).takeIf { it >= 0 }?.let { nums1[it] } ?: Int.MIN_VALUE,
            (k - l - 1).takeIf { it >= 0 }?.let { nums2[it] } ?: Int.MIN_VALUE)
        if ((len1 + len2) % 2 == 1) return c1.toDouble()
        val c2 = minOf(
            (l).takeIf { it < len1 }?.let { nums1[it] } ?: Int.MAX_VALUE,
            (k - l).takeIf { it < len2 }?.let { nums2[it] } ?: Int.MAX_VALUE)
        return (c1 + c2) * 0.5
    }

    @Test
    fun test() {
        println(findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3)))
    }
}