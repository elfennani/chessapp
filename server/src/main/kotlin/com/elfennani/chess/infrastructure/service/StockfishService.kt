package com.elfennani.chess.infrastructure.service

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class StockfishService {
    private val stockfishPath = "/Users/elfennani/Downloads/stockfish/stockfish"
    private var reader: BufferedReader
    private var writer: OutputStreamWriter
    private var process: Process
    private var isReady: Boolean = false

    init {
        val process = ProcessBuilder(stockfishPath).start()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val writer = OutputStreamWriter(process.outputStream)

        writer.write("uci\n")
        writer.flush()

        this.reader = reader
        this.writer = writer
        this.process = process
    }

    fun isReady(): Boolean {
        writer.write("isready\n")
        writer.flush()
        var isReady = false
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            if (line == "readyok") {
                isReady = true
                break
            }
        }

        this.isReady = isReady
        return isReady
    }

    fun close() {
        writer.write("quit\n")
        writer.flush()
        process.destroy()
    }

    fun getMovesForHistory(history: List<String>): List<String> {
        if (!isReady) {
            throw Exception("Stockfish engine not ready")
        }

        writer.write("position startpos moves $history\n")
        writer.flush()

        // Ask for legal moves
        writer.write("go perft 1\n")
        writer.flush()

        val moves = mutableListOf<String>()
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            if (!line!!.startsWith("info")) {
                val legalMove = line.split(":").first();
                moves.add(legalMove)
            }

            if (line.startsWith("Nodes searched:") || line == "\n" || line.isEmpty()) {
                break;
            }
        }

        return moves.filter { it.isNotEmpty() && it.isNotBlank() }
    }
}