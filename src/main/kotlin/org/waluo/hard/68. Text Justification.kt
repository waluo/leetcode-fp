package org.waluo.hard

class Solution {
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val ret = mutableListOf<String>()
        val buffer = mutableListOf<String>()
        words.forEach { word ->
            if ((buffer.map { it.length }.sum() + buffer.size + word.length) > maxWidth) {
                if (buffer.size == 1) {
                    ret.add(buffer[0].padEnd(maxWidth, ' '))
                } else {
                    val left = maxWidth - buffer.map { it.length }.sum()
                    val spaceQuantity = left / (buffer.size - 1)
                    val spacePad = left % (buffer.size - 1)
                    var str = ""
                    buffer.indices.forEach { i ->
                        str += buffer[i]
                        str += " ".repeat(spaceQuantity)
                        if (i < spacePad)
                            str += " "
                    }
                    ret.add(str.take(maxWidth))
                }
                buffer.clear()
            }
            buffer += word
        }
        if (buffer.isNotEmpty()) {
            ret.add(buffer.joinToString(separator = " ").padEnd(maxWidth, ' '))
        }
        return ret
    }
}