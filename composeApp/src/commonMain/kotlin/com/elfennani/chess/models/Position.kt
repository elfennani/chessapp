package com.elfennani.chess.models

data class Position(
    /** Row index from 0 to 7 */
    val row: Int,
    /** Column index from 0 to 7 */
    val col: Int,
)

fun Position.toChessNotation(): String {
    val file = 'a' + col
    val rank = 8 - row
    return "$file$rank"
}

fun String.toPosition(): Position? {
    if (this.length != 2) throw IllegalArgumentException("Invalid chess notation: $this")
    val file = this[0]
    val rank = this[1]

    val col = file - 'a'
    val row = 8 - (rank - '0')

    if (col !in 0..7 || row !in 0..7) throw IllegalArgumentException("Invalid chess notation: $this")

    return Position(row, col)
}

fun Pair<Position, Position>.toChessNotation(): String {
    return first.toChessNotation() + second.toChessNotation()
}

fun String.toPositionPair(): Pair<Position, Position>? {
    if (this.length != 4) throw IllegalArgumentException("Invalid move notation: $this")
    val from = this.substring(0, 2).toPosition() ?: return null
    val to = this.substring(2, 4).toPosition() ?: return null
    return Pair(from, to)
}

fun List<Pair<Position, Position>>.toChessNotationList(): List<String> {
    return this.map { it.toChessNotation() }
}

fun List<String>.toPositionPairList(): List<Pair<Position, Position>> {
    return this.mapNotNull { it.toPositionPair() }
}