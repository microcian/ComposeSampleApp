package com.techvista.etisalat.presentation.albums.pictures


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.techvista.etisalat.commom.Screens
import com.techvista.etisalat.presentation.albums.AlbumViewModel
import com.techvista.etisalat.presentation.common.ExtrasData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PictureScreen(
    navController: NavController,
    viewModel: AlbumViewModel
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        navController.currentBackStackEntry?.let {
            it.arguments?.takeIf {
                it.containsKey(ExtrasData.ALBUM_ID)
            }?.apply {
                val albumId = this.getInt(ExtrasData.ALBUM_ID)
                items(viewModel.getAlbumPictures(albumId)) { item ->
                    Card(
                        modifier = Modifier.padding(4.dp),
                        backgroundColor = Color.LightGray,
                    ) {
                        Image(
                            contentScale = ContentScale.FillBounds,
                            painter = rememberImagePainter(item.thumbnailUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .size(128.dp)
                                .clickable {
                                    navController.navigate("${Screens.PictureDetailScreen.route}/${item.albumId}/${item.id}")
                                }
                        )
                    }
                }
            }
        }
    }
}