package com.elfennani.chess.presentation.dto

import kotlinx.serialization.Serializable


@Serializable
data class MovesResponse(
    val moves: List<String>,
    val playerTurn: String? = null,
    val error: String? = null
)
