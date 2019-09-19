package org.waluo

/**
 * waluo
 * 2019-05-16.
 */
data class TreeNode(
    var `val`: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

data class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

class Memo<K, V> : (K) -> V {
    private val cache: MutableMap<K, V> = mutableMapOf()
    lateinit var f: (K) -> V
    override fun invoke(key: K): V {
        val v = cache[key] ?: f(key)
        cache.putIfAbsent(key, v)
        return v
    }
}

class Memo2<K, V>(val f: (K) -> V) : (K) -> V {
    private val cache: MutableMap<K, V> = mutableMapOf()
    override fun invoke(key: K): V {
        val v = cache[key] ?: f(key)
        cache.putIfAbsent(key, v)
        return v
    }
}