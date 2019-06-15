package org.waluo.medium

/**
 * waluo
 * 2019-06-15.
 */
fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
    fun String.onesAndZeroes(): Pair<Int, Int> {
        return this.toCharArray().fold(0 to 0) { acc, c ->
            if (c == '0') {
                acc.first + 1 to acc.second
            } else {
                acc.first to acc.second + 1
            }
        }
    }
    return strs.fold(listOf(Triple(0, m, n))) { acc, s ->
        val (zero,one ) = s.onesAndZeroes()
        acc.flatMap {
            if (it.second >= zero && it.third >= one) {
                listOf(it, Triple(it.first + 1, it.second - zero, it.third - one))
            } else {
                listOf(it)
            }
        }.distinct()
    }.map { it.first }.max() ?: 0
}