package com.elfennani.chess.di

import com.elfennani.chess.data.ApiService
import com.elfennani.chess.presentation.screens.game.GameViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val commonModules = module {
    singleOf(::ApiService)
    viewModelOf(::GameViewModel)
}