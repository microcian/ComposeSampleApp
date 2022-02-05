package com.techvista.etisalat.presentation.albums

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.techvista.etisalat.R
import com.techvista.etisalat.presentation.common.ErrorView

@Composable
fun AlbumScreen(
    navController: NavController,
    viewModel: AlbumViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.listPhotos) { photos ->
                    AlbumItem(photos = photos, onClick = { photos ->
                    })
                }
            }
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