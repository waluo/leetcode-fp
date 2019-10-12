package org.waluo.medium

fun isValidSudoku(board: Array<CharArray>): Boolean {
    fun Array<CharArray>.isValidRow(): Boolean {
        return board.all {
            val chars = it.filter { c -> c != '.' }
            chars.size == chars.distinct().size
        }
    }

    fun Array<CharArray>.isValidCol(): Boolean {
        return (0..8).map { index -> this.map { it[index] } }.all {
            val chars = it.filter { c -> c != '.' }
            chars.size == chars.distinct().size
        }
    }

    fun Array<CharArray>.isValidCell(): Boolean {
        return (0..2).map { it * 3 }.flatMap { row ->
            (0..2).map { it * 3 }.map { col ->
                this[row].drop(col).take(3) + this[row + 1].drop(col).take(3) + this[row + 2].drop(col).take(3)
            }
        }.all {
            val chars = it.filter { c -> c != '.' }
            chars.size == chars.distinct().size
        }
    }
    return board.isValidRow() && board.isValidCol() && board.isValidCell()
}

fun main() {
    val board =
        arrayOf(
            charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
            charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
            charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
            charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
            charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
            charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
            charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
            charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
            charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
        )
    println(isValidSudoku(board))
}