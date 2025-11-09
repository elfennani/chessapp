package com.elfennani.chess.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.elfennani.chess.models.Piece
import com.elfennani.chess.models.Position
import kotlin.collections.set

@Composable
fun Board(
    modifier: Modifier = Modifier,
    pieces: Map<Position, Piece>,
    selectablePositions: Set<Position>,
    selectedPosition: Position?,
    onPositionSelected: (Position) -> Unit,
) {
    Column(modifier = modifier.aspectRatio(1f).background(Color.White)) {
        for (i in 0 until 8) {
            Row(Modifier.fillMaxWidth()) {
                for (j in 0 until 8) {
                    val position = Position(row = i, col = j)
                    val piece = pieces[position]
                    val isSelected = position == selectedPosition
                    val isSelectable = selectablePositions.contains(position)

                    Box(
                        modifier = Modifier

                            .aspectRatio(1f)
                            .then(
                                if (isSelected) {
                                    Modifier.background(Color.Yellow)
                                } else if ((i + j) % 2 == 0) {
                                    Modifier.background(Color(0xFFEEEED2)) // Light square
                                } else {
                                    Modifier.background(Color(0xFF769656)) // Dark square
                                }
                            )
                            .weight(1f / 8f)
                            .clickable(
                                enabled = isSelectable
                            ) {
                                onPositionSelected(position)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (piece != null) {
                            PieceItem(type = piece.type, player = piece.player)
                        }

                        if (isSelectable) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.Green.copy(alpha = 0.25f), CircleShape)
                            )
                        }
                    }
                }
            }
        }
    }

}