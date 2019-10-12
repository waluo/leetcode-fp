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


    Files.find(Paths.get("."), 10, BiPredicate{ path: Path, _ ->
        val name = path.fileName.toString()
        name.endsWith(".kt") && name.contains("_")
    }).toList().forEach {
        val split = it.fileName.toString().split("_")

        val newPath = Paths.get(it.parent.toString(), split[0] + ". " + split.drop(1).joinToString(separator = " "))
        Files.copy(it, newPath)
        Files.delete(it)
    }

}