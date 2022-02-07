package com.techvista.etisalat.presentation.albums

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.techvista.etisalat.R
import com.techvista.etisalat.commom.Screens
import com.techvista.etisalat.presentation.common.ErrorView

@Composable
fun AlbumScreen(
    navController: NavController,
    viewModel: AlbumViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        items(state.listPhotos.keys.toList()) { key ->
            AlbumItem(name = key, onClick = {
                navController.navigate("${Screens.PictureScreen.route}/$key")
            })
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.error.isNotBlank()) {
            ErrorView(
                error = state.error, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Center))
        } else if (state.listPhotos.isEmpty()) {
            Text(
                text = "There is no data to show",
                color = colorResource(id = R.color.textWhite),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Center)
            )
        }
    }
}