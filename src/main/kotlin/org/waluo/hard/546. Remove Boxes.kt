package org.waluo.hard

import org.waluo.Memo

/**
 * waluo
 * 2019-06-24.
 */
fun removeBoxes(boxes: IntArray): Int {
    val removeBoxesMemo = Memo<Triple<Int, Int, Int>, Int>()
    removeBoxesMemo.f = { (i: Int, j: Int, k: Int) ->
        when {
            i > j -> 0
            i == j -> k * k
            else -> (i + 1..j + 1).fold(0 to 0) { (max, combo), n ->
                when {
                    n > j || boxes[i] != boxes[n] && combo > 0 -> {
                        val v1 = removeBoxesMemo(Triple(i + 1, n - combo - 1, 1))
                        val v2 = removeBoxesMemo(Triple(n - 1, j, combo + k))
                        kotlin.math.max(max, v1 + v2) to 0
                    }
                    boxes[i] == boxes[n] -> max to combo + 1
                    else -> max to combo
                }
            }.first
        }
    }
    return removeBoxesMemo(Triple(0, boxes.size - 1, 1))
}


fun removeBoxes2(boxes: IntArray): Int {
    val groupBoxes = boxes.fold(emptyList<Pair<Int, Int>>()) { acc, i ->
        if (i == acc.lastOrNull()?.first) {
            acc.dropLast(1) + (i to acc.last().second + 1)
        } else {
            acc + (i to 1)
        }
    }
    fun Int.pow2(): Int {
        return this * this
    }
    val removeBoxesMemo = Memo<List<Pair<Int, Int>>, Int>()
    removeBoxesMemo.f = { input ->
        when{
            input.isEmpty() -> 0
            input.size == 1 -> input.first().second.pow2()
            else -> (1..input.size).map { n ->
                when{
                    n == input.size -> input.first().second.pow2() + removeBoxesMemo(input.drop(1))
                    input[0].first == input[n].first -> {
                        val v1 = removeBoxesMemo(input.subList(1, n))
                        val v2 = removeBoxesMemo(listOf(input[n].let {
                            it.first to it.second + input[0].second
                        }) + input.drop(n + 1))
                        v1 + v2
                    }
                    else -> 0
                }
            }.max() ?: 0
        }
    }
    return removeBoxesMemo(groupBoxes)
}

fun main() {
    val ints = intArrayOf(3,8,8,5,5,3,9,2,4,4,6,5,8,4,8,6,9,6,2,8,6,4,1,9,5,3,10,5,3,3,9,8,8,6,5,3,7,4,9,6,3,9,4,3,5,10,7,6,10,7)
    println(removeBoxes(ints))
    println(removeBoxes2(ints))
}