package org.waluo.medium

import org.junit.jupiter.api.Test
import org.waluo.ListNode

class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        fun ListNode?.len(): Int =
            if (this == null) 0
            else this.next.len() + 1

        fun add(l1: ListNode?, l2: ListNode?): Int {
            if (l1 == null || l2 == null) return 0
            val sum = add(l1.next, l2.next) + l1.`val` + l2.`val`
            l1.`val` = sum % 10
            return sum / 10
        }

        var (long, short) = if (l1.len() >= l2.len()) { l1 to l2 } else { l2 to l1 }

        for (i in 0..(long.len() - short.len())) {
            val temp = ListNode(0).apply { next = short }
            short = temp
        }

        short!!.`val` = add(short.next, long)

        return if (short.`val` == 0) short.next else short
    }

    @Test
    fun test() {
        val node1 = ListNode(1, null)
        val node2 = ListNode(9, ListNode(9, null))
        println(addTwoNumbers(node1, node2))
    }
}