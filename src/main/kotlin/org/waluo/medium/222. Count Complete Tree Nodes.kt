package org.waluo.medium

import org.waluo.TreeNode

class Solution222 {
    fun countNodes(root: TreeNode?): Int {
        val leftDepth = generateSequence(root){it.left}.count()
        val rightDepth = generateSequence(root){it.right}.count()
        if (leftDepth == rightDepth) {
            return (1 shl leftDepth) - 1
        }
        return 1 + countNodes(root?.left) + countNodes(root?.right)
    }
}