package org.waluo.medium

/**
 * waluo
 * 2019-05-14.
 */

fun findDuplicate(paths: Array<String>): List<List<String>> {
    val groupBy = paths.flatMap { input ->
        val list = input.split("\\s+".toRegex())
        val path = list.first()
        list.drop(1).map { it.split("(", ")") }
            .map { it[1] to "$path/${it[0]}" }
    }.groupBy { it.first }
    return groupBy.values
        .filter { it.size > 1 }
        .map { it.map { pair -> pair.second } }
}


fun main() {
    println(
        findDuplicate(
            arrayOf(
                "root/a 1.txt(abcd) 2.txt(efgh)",
                "root/c 3.txt(abcd)",
                "root/c/d 4.txt(efgh)",
                "root 4.txt(efgh)"
            )
        )
    )
}