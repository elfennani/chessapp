package com.elfennani.chess.infrastructure.di

import com.elfennani.chess.domain.repository.StockfishRepository
import com.elfennani.chess.infrastructure.repository.StockfishRepositoryImpl
import com.elfennani.chess.infrastructure.service.StockfishService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModules = module {
    singleOf(::StockfishService)
    single<StockfishRepository> {
        StockfishRepositoryImpl(stockfishService = get())
    }
}