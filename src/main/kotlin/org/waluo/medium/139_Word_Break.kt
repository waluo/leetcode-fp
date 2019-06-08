package org.waluo.medium

/**
 * waluo
 * 2019-06-08.
 */
fun wordBreak(s: String, wordDict: List<String>): Boolean {
    return s.length == (1..s.length).fold(listOf(0)) { acc, i ->
        if (acc.any { x -> wordDict.any { it == s.substring(x, i) } }) {
            acc + i
        } else {
            acc
        }
    }.lastOrNull()
}