package org.waluo.medium

import org.junit.jupiter.api.Test
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

class Trie {

    val root = Node('0')

    /** Initialize your data structure here. */
    data class Node(
        val char: Char,
        val count: AtomicLong = AtomicLong(0),
        val leaf: AtomicLong = AtomicLong(0),
        val child: ConcurrentHashMap<Char, Node> = ConcurrentHashMap(30)
    )

    /** Inserts a word into the trie. */
    fun insert(word: String) {
        word.indices.fold(root) { acc, i ->
            if(!acc.child.containsKey(word[i]))
                acc.child.putIfAbsent(word[i], Node(word[i]))
            val next = acc.child[word[i]]!!
            next.apply { this.count.incrementAndGet() }
        }.leaf.incrementAndGet()
    }

    /** Returns if the word is in the trie. */
    fun search(word: String): Boolean {
        return word.indices.fold(root) { acc, i ->
            acc.child[word[i]]?.takeIf { it.count.get() > 0 } ?: return false
        }.leaf.get() > 0
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    fun startsWith(prefix: String): Boolean {
        prefix.indices.fold(root) { acc, i ->
            acc.child[prefix[i]] ?: return false
        }
        return true
    }

    @Test
    fun test() {
        val trie = Trie()
        trie.insert("apple")
        println(trie.search("apple"))
        println(trie.search("app"))
        println(trie.startsWith("app"))
    }

}