package org.waluo

fun doesCircleExist(commands: Array<String>): Array<String> {
    return commands.map { doesCircleExist(it) }.toTypedArray()
}

data class Status(
    val x: Int = 0,
    val y: Int = 0,
    val direction: Int = 0
)

fun doesCircleExist(commands: String): String {
    return commands.repeat(4).fold(Status()) { acc, c ->
        when (c) {
            'R' -> acc.copy(direction = (acc.direction + 1) % 4)
            'L' -> acc.copy(direction = (acc.direction + 3) % 4)
            else -> when (acc.direction) {
                0 -> acc.copy(x = acc.x + 1)
                1 -> acc.copy(y = acc.y - 1)
                2 -> acc.copy(x = acc.x - 1)
                else -> acc.copy(y = acc.y + 1)
            }
        }
    }.let { if (it.x == 0 && it.y == 0) "YES" else "NO" }
}

fun triangleOrNot(a: Array<Int>, b: Array<Int>, c: Array<Int>): Array<String> {
    return a.indices.map { i ->
        val sum = a[i] + b[i] + c[i]
        val max = maxOf(a[i], b[i], c[i])
        if (max * 2 >= sum) "No" else "Yes"
    }.toTypedArray()
}

fun consecutive(num: Long): Int {
    var count = 0
    var sum = 3L
    var n = 2
    while (sum <= num) {
        if ((num - sum) % n == 0L) count++
        sum += ++n
    }
    return count
}

fun maxStep(n: Int, k: Int): Int {
    var sum = 0
    for (i in (1..k)) {
        sum += i
        if (sum == k) sum--
    }
    return sum
}

fun commonPrefix(inputs: Array<String>): Array<Int> =
    inputs.map { commonPrefix(it) }.toTypedArray()

fun commonPrefix(input: String): Int {
    var sum = input.length
    for (drop in (1 until input.length)) {
        var index = 0
        while ((index + drop) < input.length && input[index + drop] == input[index++]) sum++
    }
    return sum
}

fun numberOfPaths(a: Array<Array<Int>>): Int {
    val dp = Array(a.size) { IntArray(a.first().size) }
    (a.indices).takeWhile { a[it][0] == 1 }.forEach { dp[it][0] = 1 }
    (a.first().indices).takeWhile { a[0][it] == 1 }.forEach { dp[0][it] = 1 }
    (1 until a.size).forEach { i ->
        (1 until a.first().size).filter { a[i][it] == 1 }.forEach { j ->
            dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007
        }
    }
    return dp.last().last()
}

fun main() {
    println(
        numberOfPaths(
            arrayOf(
                arrayOf(1, 0, 1, 1, 1),
                arrayOf(1, 0, 0, 0, 0),
                arrayOf(1, 0, 1, 1, 1),
                arrayOf(1, 1, 1, 1, 1)
            )
        )
    )
}