package org.waluo.hard

import org.waluo.Memo
import org.waluo.Memo2

/**
 * waluo
 * 2019-06-08.
 */
fun wordBreak(s: String, wordDict: List<String>): List<String> {
    lateinit var wordBreakMemo: Memo2<String, List<String>>
    wordBreakMemo = Memo2{ str ->
        when {
            str.isBlank() -> listOf("")
            else -> wordDict.fold(emptyList()) { acc, c ->
                if (str.startsWith(c) && wordBreakMemo(str.drop(c.length)).isNotEmpty()) {
                    acc + wordBreakMemo(str.drop(c.length)).map {
                        if (it.isNotBlank()) "$c $it" else c
                    }
                } else acc
            }
        }
    }
    return wordBreakMemo(s)
}