package org.waluo.medium

/**
 * waluo
 * 2019-05-15.
 */
fun findPoisonedDuration(timeSeries: IntArray, duration: Int): Int {
    return if (timeSeries.isEmpty()) {
        0
    } else {
        timeSeries.map { 0 to it }
            .reduce { left, right ->
                if (left.second + duration < right.second) {
                    (left.first + duration) to right.second
                } else {
                    (left.first + (right.second - left.second)) to right.second
                }
            }.first + duration
    }
}