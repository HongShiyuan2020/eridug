package com.syhong.eridug.ui.manga_rank.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.syhong.eridug.ui.manga_rank.vm.MangaRankViewModel

@Composable
fun MangaRank(
    viewModel: MangaRankViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Box() {

    }
}