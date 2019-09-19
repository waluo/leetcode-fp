package org.waluo.playground

import org.waluo.ListNode

fun reverse(list: ListNode?): ListNode? {
    if(list == null) return null
    val next = list.next ?: return list
    val reverse = reverse(next)
    list.next = null
    next.next = list
    return reverse
}

fun main() {
    val node = ListNode(1, ListNode(2, ListNode(3, null)))
    println(reverse(node))
}