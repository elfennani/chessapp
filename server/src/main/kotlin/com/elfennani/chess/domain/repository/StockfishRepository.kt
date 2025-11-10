package com.elfennani.chess.domain.repository

interface StockfishRepository {
    suspend fun getMovesWithHistory(moveHistory: List<String>): List<String>
}