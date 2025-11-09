package com.elfennani.chess

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }

        get("/moves") {
            val historyParam = call.request.queryParameters["history"] ?: ""
            val moveHistory =
                if (historyParam.isNotEmpty()) historyParam.split(",") else emptyList()

            val stockfishPath = "/Users/elfennani/Downloads/stockfish/stockfish"
            try {
                val moves = getMovesForSquareWithHistory(stockfishPath, moveHistory)

                call.respondText(moves.joinToString(","))
            } catch (e: Exception) {
                call.respondText(
                    "Error getting moves: ${e.message}",
                    status = HttpStatusCode.InternalServerError
                )
            }
        }
    }
}

fun getMovesForSquareWithHistory(
    stockfishPath: String,
    moveHistory: List<String>
): List<String> {
    val process = ProcessBuilder(stockfishPath).start()
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    val writer = OutputStreamWriter(process.outputStream)

    val initialLine = reader.readLine() // Stockfish version info
    println("Stockfish: $initialLine")
    writer.write("uci\n")
    writer.flush()
    writer.write("isready\n")
    writer.flush()
    var isReady = false
    var line: String?
    while(reader.readLine().also { line = it } != null) {
        if (line == "readyok") {
            isReady = true
            break
        }
    }
    if (!isReady) {
        throw Exception("Stockfish engine not ready")
    }

    // Setup starting position + previous moves
    val movesLine = if (moveHistory.isNotEmpty()) moveHistory.joinToString(" ") else ""
    writer.write("position startpos moves $movesLine\n")
    writer.flush()

    // Ask for legal moves
    writer.write("go perft 1\n")
    writer.flush()

    val moves = mutableListOf<String>()

    while (reader.readLine().also { line = it } != null) {
        if (!line!!.startsWith("info")) {
            val legalMove = line!!.split(":").first();
            moves.add(legalMove)
        }

        if (line.startsWith("Nodes searched:") || line == "\n" || line.isEmpty()) {
            break;
        }
    }

    writer.write("quit\n")
    writer.flush()
    process.waitFor()

    return moves
}