package org.waluo.hard

class Solution10 {
    val cache = mutableMapOf<Pair<String, String>, Boolean>()

    fun isMatch(s: String, p: String): Boolean {
        val cacheKey = s to p
        fun Pair<String, String>.firstMatch() =
            first.isNotEmpty() && (first.first() == second.first() || second.first() == '.')
        return when {
            s.isEmpty() && p.isEmpty() -> true
            p.isEmpty() -> false
            else -> cache.getOrPut(cacheKey) {
                val firstMatch = cacheKey.firstMatch()
                if (p.length > 1 && p[1] == '*') isMatch(s, p.drop(2)) || firstMatch && isMatch(s.drop(1), p)
                else firstMatch && isMatch(s.drop(1), p.drop(1))
            }
        }
    }
}