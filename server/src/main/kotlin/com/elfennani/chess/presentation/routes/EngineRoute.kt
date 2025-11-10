package com.elfennani.chess.presentation.routes

import com.elfennani.chess.domain.repository.StockfishRepository
import com.elfennani.chess.presentation.dto.MovesResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject

fun Route.engineRoutes() {
    val stockfishRepository by inject<StockfishRepository>()
    get("/moves") {
        val history = call.request.queryParameters.getAll("history") ?: emptyList()

        try {
            val moves = stockfishRepository.getMovesWithHistory(history)
            val response = MovesResponse(
                moves = moves,
                playerTurn = if (history.size % 2 == 0) "white" else "black",
                error = null
            )

            call.respond(response)
        } catch (e: Exception) {
            call.respondText(
                "Error processing request: ${e.message}",
                status = HttpStatusCode.InternalServerError
            )
        }
    }
}