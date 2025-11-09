package com.elfennani.chess.presentation.screens.game

import com.elfennani.chess.models.Piece
import com.elfennani.chess.models.PieceType
import com.elfennani.chess.models.Player
import com.elfennani.chess.models.Position
import com.elfennani.chess.models.toPosition

private val defaultPieces = mapOf(
    "a1".toPosition()!! to Piece(PieceType.Rook, Player.White),
    "b1".toPosition()!! to Piece(PieceType.Knight, Player.White),
    "c1".toPosition()!! to Piece(PieceType.Bishop, Player.White),
    "d1".toPosition()!! to Piece(PieceType.Queen, Player.White),
    "e1".toPosition()!! to Piece(PieceType.King, Player.White),
    "f1".toPosition()!! to Piece(PieceType.Bishop, Player.White),
    "g1".toPosition()!! to Piece(PieceType.Knight, Player.White),
    "h1".toPosition()!! to Piece(PieceType.Rook, Player.White),
    "a2".toPosition()!! to Piece(PieceType.Pawn, Player.White),
    "b2".toPosition()!! to Piece(PieceType.Pawn, Player.White),
    "c2".toPosition()!! to Piece(PieceType.Pawn, Player.White),
    "d2".toPosition()!! to Piece(PieceType.Pawn, Player.White),
    "e2".toPosition()!! to Piece(PieceType.Pawn, Player.White),
    "f2".toPosition()!! to Piece(PieceType.Pawn, Player.White),
    "g2".toPosition()!! to Piece(PieceType.Pawn, Player.White),
    "h2".toPosition()!! to Piece(PieceType.Pawn, Player.White),
    "a8".toPosition()!! to Piece(PieceType.Rook, Player.Black),
    "b8".toPosition()!! to Piece(PieceType.Knight, Player.Black),
    "c8".toPosition()!! to Piece(PieceType.Bishop, Player.Black),
    "d8".toPosition()!! to Piece(PieceType.Queen, Player.Black),
    "e8".toPosition()!! to Piece(PieceType.King, Player.Black),
    "f8".toPosition()!! to Piece(PieceType.Bishop, Player.Black),
    "g8".toPosition()!! to Piece(PieceType.Knight, Player.Black),
    "h8".toPosition()!! to Piece(PieceType.Rook, Player.Black),
    "a7".toPosition()!! to Piece(PieceType.Pawn, Player.Black),
    "b7".toPosition()!! to Piece(PieceType.Pawn, Player.Black),
    "c7".toPosition()!! to Piece(PieceType.Pawn, Player.Black),
    "d7".toPosition()!! to Piece(PieceType.Pawn, Player.Black),
    "e7".toPosition()!! to Piece(PieceType.Pawn, Player.Black),
    "f7".toPosition()!! to Piece(PieceType.Pawn, Player.Black),
    "g7".toPosition()!! to Piece(PieceType.Pawn, Player.Black),
    "h7".toPosition()!! to Piece(PieceType.Pawn, Player.Black),
)

data class GameUiState(
    val pieces: Map<Position, Piece> = defaultPieces,
    val selectablePositions: Set<Position> = emptySet(),
    val selectedPosition: Position? = null,
    val currentPlayer: Player = Player.White,
    val isPending: Boolean = false,
)
