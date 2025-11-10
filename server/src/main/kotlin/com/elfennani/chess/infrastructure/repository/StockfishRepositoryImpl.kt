package com.elfennani.chess.infrastructure.repository

import com.elfennani.chess.domain.repository.StockfishRepository
import com.elfennani.chess.infrastructure.service.StockfishService

class StockfishRepositoryImpl(
    private val stockfishService: StockfishService
) : StockfishRepository {
    override suspend fun getMovesWithHistory(moveHistory: List<String>): List<String> {
        val stockfish = StockfishService()

        if (!stockfish.isReady()) {
            throw Exception("Stockfish engine not ready")
        }

        return stockfish.getMovesForHistory(history = moveHistory)
    }
}