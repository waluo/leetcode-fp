package org.waluo.hard

import org.waluo.TreeNode

data class Buffer(
    val depth: Int = 0,
    val number: String = "",
    val depthToNode: Map<Int, TreeNode> = emptyMap()
)

fun recoverFromPreorder(S: String): TreeNode? {
    fun Buffer.addNode(): Map<Int, TreeNode> {
        val node = TreeNode(number.toInt())
        return depthToNode[depth - 1]?.let {
            if (it.left == null) it.left = node
            else it.right = node
            depthToNode - depth + (depth to node)
        } ?: mapOf(0 to node)
    }
    val buffer = S.fold(Buffer()) { acc, c ->
        when {
            c == '-' && acc.number.isNotEmpty() -> Buffer(depth = 1, depthToNode = acc.addNode())
            c == '-' && acc.number.isEmpty() -> acc.copy(depth = acc.depth + 1)
            acc.number.isEmpty() -> acc.copy(number = c.toString())
            else -> acc.copy(number = acc.number + c.toString())
        }
    }
    return buffer.addNode()[0]
}