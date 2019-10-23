package org.waluo

import java.lang.StringBuilder

fun reverse(list: ListNode?): ListNode? {
    if (list == null) return null
    val next = list.next ?: return list
    val reverse = reverse(next)
    list.next = null
    next.next = list
    return reverse
}

fun sockMerchant(n: Int, ar: Array<Int>): Int {
    return ar.groupingBy { it }
        .eachCount()
        .values.map { it / 2 }.sum()
}

fun jumpingOnClouds(c: Array<Int>): Int {
    return generateSequence(0) {
        if (it + 2 >= c.size || c[it + 2] == 0) it + 2 else it + 1
    }.takeWhile { it < c.size - 1 }.count()
}

fun countingValleys(n: Int, s: String): Int {
    var curr = 0
    var ret = 0
    s.map { if (it == 'U') 1 else -1 }.forEach { i ->
        val next = curr + i
        if (curr == 0 && next == -1) {
            ret++
        }
        curr = next
    }
    return ret
}

fun nonDivisibleSubset(k: Int, s: Array<Int>): Int {
    val counts = s.map { it % k }
        .groupingBy { it }
        .eachCount()

    var count = (counts[0] ?: 0).coerceAtMost(1)

    (1 until k).filter { it + it < k }.forEach { i ->
        count += maxOf(counts[i] ?: 0, counts[k - i] ?: 0)
    }
    if (k % 2 == 0) {
        count += (counts[k / 2] ?: 0).coerceAtMost(1)
    }

    return count
}

fun main() {
    csvParser2("Weronika,Zaborska,njkfdsv@dsgfk.sn,\"running\"\", sci-fi\",new,Krakow,25")
        .forEach { println(it) }
}

// Weronika,Zaborska,njkfdsv@dsgfk.sn,"running"", sci-fi",new,Krakow,25
fun csvParser(line: String): MutableList<String> {
    val ret = mutableListOf<String>()
    var buffer = ""
    line.split(",").forEach { str ->
        if (buffer.isEmpty() && (str.isEmpty() || str[0] != '"')) {
            ret.add(str)
        } else {
            buffer += "$str,"
            if (buffer.filter { it == '"' }.count() % 2 == 0) {
                ret.add(buffer.drop(1).dropLast(2).replace("\"\"", "\""))
                buffer = ""
            }
        }
    }
    return ret
}

fun csvParser2(line: String): List<String> {
    return sequence {
        val buffer = StringBuilder()
        var count = 0
        line.toCharArray().forEach { c ->
            when {
                c == '"' && count++ % 2 == 0 -> Unit
                c == ',' && count % 2 == 0 -> {
                    yield(buffer.dropLastWhile { it == '"' }.toString())
                    buffer.clear()
                }
                else -> buffer.append(c)
            }
        }
        yield(buffer.dropLastWhile { it == '"' }.toString())
    }.toList()
}