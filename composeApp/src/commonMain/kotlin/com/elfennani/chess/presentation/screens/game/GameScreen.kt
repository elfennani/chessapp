package com.elfennani.chess.presentation.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.elfennani.chess.models.Player
import com.elfennani.chess.models.Position
import com.elfennani.chess.presentation.components.Board
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameScreen(navHostController: NavHostController) {
    val viewModel = koinViewModel<GameViewModel>()
    val state by viewModel.state.collectAsState()
    GameScreen(
        state = state,
        onSelectPosition = viewModel::onSelectPosition
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GameScreen(
    state: GameUiState,
    onSelectPosition: (position: Position) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Game")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
                .consumeWindowInsets(it),
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterVertically
            ),
        ) {
            Text(
                "White",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .alpha(
                        if (state.currentPlayer == Player.White) 1f else 0.7f
                    ),
                textAlign = TextAlign.Center,
                fontWeight = if (state.currentPlayer == Player.White)
                    FontWeight.Bold
                else FontWeight.Normal,
            )
            Board(
                modifier = Modifier.padding(16.dp).alpha(if (state.isPending) 0.5f else 1f),
                pieces = state.pieces,
                selectablePositions = state.selectablePositions,
                selectedPosition = state.selectedPosition,
                onPositionSelected = onSelectPosition
            )
            Text(
                "Black",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .alpha(
                        if (state.currentPlayer == Player.Black) 1f else 0.7f
                    ),
                textAlign = TextAlign.Center,
                fontWeight = if (state.currentPlayer == Player.Black)
                    FontWeight.Bold
                else FontWeight.Normal,
            )
        }
    }
}