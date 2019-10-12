package org.waluo.medium

/**
 * waluo
 * 2019-07-01.
 */
fun isNStraightHand(hand: IntArray, W: Int): Boolean {
    return hand.toList().sorted()
        .fold(emptyList<MutableList<Int>>()){ acc, i ->
            val list = acc.firstOrNull { it.last() + 1 == i }
            if(list == null){
                acc + listOf(mutableListOf(i))
            }else{
                list.add(i)
                acc
            }.filter { it.size < W }
    }.isEmpty()
}