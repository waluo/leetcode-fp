package org.waluo.medium

import org.waluo.TreeNode

/**
 * waluo
 * 2019-05-16.
 */


fun levelOrder(root: TreeNode?): List<List<Int>> {
    fun recurse(queue: List<TreeNode>, ret: List<List<Int>>): List<List<Int>> {
        return if(queue.isEmpty()){
            ret
        }else{
            val next = queue.flatMap { listOfNotNull(it.left, it.right) }
            recurse(next, ret + listOf(queue.map { it.`val` }))
        }
    }
    return recurse(listOfNotNull(root), emptyList())
}