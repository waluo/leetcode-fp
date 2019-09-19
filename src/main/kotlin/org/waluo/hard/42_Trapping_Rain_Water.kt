package org.waluo.hard

import org.junit.jupiter.api.Test

class Solution42 {
    fun trap(height: IntArray): Int {
        val lMax = IntArray(height.size){0}
        val rMax = IntArray(height.size){0}
        (height.indices).fold(0){ acc, i ->
            lMax[i] = acc
            maxOf(acc, height[i])
        }
        (height.indices.reversed()).fold(0){ acc, i ->
            rMax[i] = acc
            maxOf(acc, height[i])
        }
        return (height.indices).fold(0){acc, i ->
            acc + (minOf(lMax[i], rMax[i]) - height[i]).coerceAtLeast(0)
        }
    }

    @Test
    fun test(){
        trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1))
    }
}