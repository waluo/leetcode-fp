package org.waluo.hard

import org.waluo.TreeNode

class Solution124 {
    fun maxPathSum(root: TreeNode?): Int {
        return root?.let { maxRootAndPathSum(it).second } ?: 0
    }
    private fun maxRootAndPathSum(root: TreeNode?): Pair<Int, Int> {
        if (root == null) {
            return 0 to Int.MIN_VALUE
        }
        val (leftRoot, leftPath) = maxRootAndPathSum(root.left)
        val (rightRoot, rightPath) = maxRootAndPathSum(root.right)
        val rootSum = maxOf(leftRoot, rightRoot, 0) + root.`val`
        val rootPath = maxOf(leftRoot, 0) + root.`val` +  maxOf(rightRoot, 0)
        return rootSum to maxOf(rootPath, leftPath, rightPath)
    }
}