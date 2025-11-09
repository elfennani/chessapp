package com.elfennani.chess.models

data class Piece(
    val type: PieceType,
    val player: Player,
    val hasMoved: Boolean = false
)
