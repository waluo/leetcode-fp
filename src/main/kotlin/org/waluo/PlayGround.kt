package org.waluo

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes
import java.util.function.BiPredicate
import kotlin.streams.toList

fun reverse(list: ListNode?): ListNode? {
    if(list == null) return null
    val next = list.next ?: return list
    val reverse = reverse(next)
    list.next = null
    next.next = list
    return reverse
}

fun main() {
}