package org.waluo.medium

import org.waluo.TreeNode

/**
 * waluo
 * 2019-05-16.
 */
fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    fun recurse(queue: List<TreeNode>, ret: List<List<Int>>, count: Int): List<List<Int>> {
        return if (queue.isEmpty()) {
            ret
        } else {
            val next = queue.flatMap { listOfNotNull(it.left, it.right) }
            val element = if (count % 2 == 1)
                queue.map { it.`val` }
            else
                queue.map { it.`val` }.reversed()
            recurse(next, ret + listOf(element), count + 1)
        }
    }
    return recurse(listOfNotNull(root), emptyList(), 1)
}

