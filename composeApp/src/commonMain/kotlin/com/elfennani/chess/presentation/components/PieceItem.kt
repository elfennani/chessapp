package com.elfennani.chess.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.elfennani.chess.models.Player
import com.elfennani.chess.models.PieceType

@Composable
fun PieceItem(type: PieceType, player: Player){
    Text(
        text = when (type) {
            PieceType.King -> if (player == Player.White) "♔" else "♚"
            PieceType.Queen -> if (player == Player.White) "♕" else "♛"
            PieceType.Rook -> if (player == Player.White) "♖" else "♜"
            PieceType.Bishop -> if (player == Player.White) "♗" else "♝"
            PieceType.Knight -> if (player == Player.White) "♘" else "♞"
            PieceType.Pawn -> if (player == Player.White) "♙" else "♟"
        },
        fontSize = 32.sp,
        color = Color.Black
    )
}