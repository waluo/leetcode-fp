package org.waluo.easy

import org.waluo.TreeNode

class Solution543 {
    fun diameterOfBinaryTree(root: TreeNode?): Int {
        fun _diameterOfBinaryTree(root: TreeNode?): Pair<Int, Int> {
            if(root == null){
                return 0 to 0
            }
            val (maxL, maxLRoot) = _diameterOfBinaryTree(root.left)
            val (maxR, maxRRoot) = _diameterOfBinaryTree(root.right)
            return maxOf(maxL, maxR) + 1 to maxOf(maxL + maxR, maxLRoot, maxRRoot)
        }
        return _diameterOfBinaryTree(root).second
    }
}