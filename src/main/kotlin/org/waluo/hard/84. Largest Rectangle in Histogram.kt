package org.waluo.hard

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class Solution84 {
    fun largestRectangleArea(heights: IntArray): Int {
        val stack = Stack<Int>()
        return (0..heights.size).fold(0) { acc, index ->
            var max = acc
            while (!stack.empty() && (index == heights.size || heights[stack.peek()] > heights[index])) {
                val h = heights[stack.pop()]
                val l = stack.takeIf { !it.empty() }?.peek() ?: -1
                max = max.coerceAtLeast(h * (index - l - 1))
            }
            stack.push(index)
            max
        }
    }

    fun largestRectangleArea2(heights: IntArray): Int {
        val stack = IntArray(heights.size)
        var stackIndex = -1
        var max = 0
        for (index in 0..heights.size) {
            while (stackIndex > -1 && (index == heights.size || heights[stack[stackIndex]] > heights[index])) {
                val h = heights[stack[stackIndex--]]
                val l = if(stackIndex > -1) { stack[stackIndex] } else -1
                max = max.coerceAtLeast(h * (index - l - 1))
            }
            stack[++stackIndex] = index
        }
        return max
    }

    @Test
    fun test() {
        Assertions.assertEquals(largestRectangleArea2(intArrayOf(2, 1, 5, 6, 2, 3)), 10)
    }
}