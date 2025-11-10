package com.elfennani.chess.infrastructure.ktor

import com.elfennani.chess.infrastructure.di.appModules
import com.elfennani.chess.presentation.routes.engineRoutes
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin

fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    install(Koin) {
        modules(appModules)
    }
    routing {
        engineRoutes()
    }
}