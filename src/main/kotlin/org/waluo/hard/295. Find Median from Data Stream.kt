package org.waluo.hard

import java.util.*
import kotlin.math.absoluteValue

class MedianFinder {
    private val maxHeap = PriorityQueue<Int>(reverseOrder())
        .apply { this.add(Int.MIN_VALUE) }
    private val minHeap = PriorityQueue<Int>()
        .apply { this.add(Int.MAX_VALUE) }

    fun addNum(num: Int) {
        maxHeap.add(num)
        minHeap.add(maxHeap.poll())
        if (maxHeap.size < minHeap.size) {
            maxHeap.add(minHeap.poll())
        }
    }

    fun findMedian(): Double =
        if (maxHeap.size == minHeap.size) (maxHeap.peek() + minHeap.peek()) / 2.0
        else (maxHeap.peek()).toDouble()
}

class MedianFinder1 {

    private val lo = arrayListOf<Int>()
    private val mid = (0..100).map { 0 }.toMutableList()
    private var midSize = 0
    private val hi = arrayListOf<Int>()

    fun addNum(num: Int) {
        when {
            num > 100 -> hi.add(hi.binarySearch(num).let { if (it < 0) it.absoluteValue - 1 else it }, num)
            num < 0 -> lo.add(lo.binarySearch(num).let { if (it < 0) it.absoluteValue - 1 else it }.absoluteValue, num)
            else -> {
                mid[num]++
                midSize++
            }
        }
    }

    private fun getK(num: Int): Int =
        when {
            num <= lo.size -> lo[num - 1]
            num <= (lo.size + midSize) ->
                mid.withIndex().fold(num - lo.size to 0) { acc, indexedValue ->
                    if (acc.first <= 0)
                        0 to acc.second
                    else
                        acc.first - indexedValue.value to indexedValue.index
                }.second
            else -> hi[num - lo.size - midSize - 1]
        }


    fun findMedian(): Double {
        val size = lo.size + midSize + hi.size
        val half = size / 2
        return when {
            size == 0 -> return 0.0
            size % 2 == 0 -> (getK(half) + getK(half + 1)) / 2.0
            else -> getK(half + 1).toDouble()
        }
    }

}