package org.waluo.failure

// Memory Limit Exceeded TODO
fun numBusesToDestination(routes: Array<IntArray>, S: Int, T: Int): Int {
    val map = routes
        .flatMap { route -> route.map { it to route } }
        .fold(mutableMapOf<Int, MutableSet<Int>>()){ acc, pair ->
            val set = acc.getOrPut(pair.first, {mutableSetOf()})
            pair.second.forEach { set += it }
            acc
        }

    var canGo = listOf(setOf(S))

    for (i in (0..routes.size)) {
        if (canGo.any { it.contains(T)}) {
            return i
        } else {
            canGo = canGo.asSequence().flatten().mapNotNull  { map[it]  }.toList()
        }
    }
    return -1
}