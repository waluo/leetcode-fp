package org.waluo.medium


class Solution17 {
    val map = mapOf(
        '2' to "abc", '3' to "def",
        '4' to "ghi", '5' to "jkl", '6' to "mno",
        '7' to "pqrs", '8' to "tuv", '9' to "wxyz"
    )

    fun letterCombinations(digits: String): List<String> =
        digits.map(map::getValue).fold(listOf("")) { acc, s ->
            acc.flatMap { prefix -> s.map { prefix + it } }
        }.filter(String::isNotEmpty)

}