package com.elfennani.chess.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText

class ApiService {
    private val client = HttpClient()
    private val baseUrl = "http://10.97.44.76:8080"

    suspend fun getMoves(history: List<String>): List<String> {
        val response = client.get("$baseUrl/moves") {
            parameter("history", history.joinToString(","))
        }
        val body = response.bodyAsText()

        return if (body.isEmpty()) {
            emptyList()
        } else {
            body.split(",").filter { !it.isBlank() }.map {
                it
                    .trim()
                    .substring(0, 4)
            }
        }
    }
}