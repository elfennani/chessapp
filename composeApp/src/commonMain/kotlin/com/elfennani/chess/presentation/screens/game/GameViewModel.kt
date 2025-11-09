package com.elfennani.chess.presentation.screens.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elfennani.chess.data.ApiService
import com.elfennani.chess.models.PieceType
import com.elfennani.chess.models.Player
import com.elfennani.chess.models.Position
import com.elfennani.chess.models.toChessNotationList
import com.elfennani.chess.models.toPositionPairList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val apiService: ApiService
) : ViewModel() {
    private val _state = MutableStateFlow(GameUiState(isPending = true))
    val state = _state.asStateFlow()

    private val history = MutableStateFlow<List<Pair<Position, Position>>>(emptyList())
    private var possibleMoves: List<Pair<Position, Position>> =
        emptyList()

    init {
        viewModelScope.launch {
            history.collect { history ->
                _state.update { state -> state.copy(isPending = true) }
                val moves = apiService.getMoves(history = history.toChessNotationList())
                possibleMoves = moves.toPositionPairList()
                _state.update {
                    it.copy(
                        isPending = false,
                        selectablePositions = possibleMoves.map { move -> move.first }.toSet()
                    )
                }
            }
        }
    }

    fun onSelectPosition(position: Position) {
        if (_state.value.isPending) return
        val piece = _state.value.pieces[position]

        if (_state.value.selectedPosition == null) {
            if (piece != null && piece.player == _state.value.currentPlayer) {
                val selectablePositions = possibleMoves
                    .filter { it.first == position }
                    .map { it.second }
                    .toSet()

                _state.update {
                    it.copy(
                        selectedPosition = position,
                        selectablePositions = selectablePositions
                    )
                }
            }
        } else {
            val from = _state.value.selectedPosition!!
            val to = position

            if (possibleMoves.contains(Pair(from, to))) {
                val movingPiece = _state.value.pieces[from]!!
                val updatedPieces = _state.value.pieces.toMutableMap()
                updatedPieces.remove(from)
                updatedPieces[to] = movingPiece

                val nextPlayer = if (_state.value.currentPlayer == Player.White) {
                    Player.Black
                } else {
                    Player.White
                }

                val newHistory = history.value + Pair(from, to)
                history.value = newHistory

                val nextSelectablePositions = updatedPieces
                    .filter { it.value.type == PieceType.Pawn && it.value.player == nextPlayer }
                    .keys

                _state.update {
                    it.copy(
                        pieces = updatedPieces,
                        selectedPosition = null,
                        selectablePositions = nextSelectablePositions,
                        currentPlayer = nextPlayer
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        selectedPosition = null,
                        selectablePositions = emptySet()
                    )
                }
            }
        }
    }
}