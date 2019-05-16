package org.waluo.hard

import org.waluo.ListNode

/**
 * waluo
 * 2019-05-14.
 */

fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
    val pair = head.reverse(k)
    return when {
        pair.first == null -> head
        pair.second == null -> pair.first
        else -> {
            head!!.next = reverseKGroup(pair.second, k)
            pair.first
        }
    }
}

fun ListNode?.reverse(k: Int): Pair<ListNode?, ListNode?> {
    if (k == 1) {
        return this to this?.next
    }
    val tail = this?.next ?: return null to null
    val (reverse, remain) = tail.reverse(k - 1)
    return if (reverse == null) {
        null to null
    } else {
        tail.next = this
        this.next = null
        reverse to remain
    }
}

fun String.toListNode(): ListNode? {
    val initial = ListNode(0)
    this.split("->")
        .map { ListNode(it.toInt()) }
        .fold(initial) { r, t ->
            r.next = t
            t
        }
    return initial.next
}

fun ListNode?.show(): String {
    fun ListNode.toList(): List<Int> {
        return listOf(this.`val`) + (this.next?.toList() ?: emptyList())
    }
    return this?.toList()?.joinToString("->") ?: "null"
}

fun main() {
    println(reverseKGroup("1->2->3->4->5".toListNode(), 2).show())
    println(reverseKGroup("1->2->3->4->5".toListNode(), 3).show())
}