package com.techvista.etisalat.presentation.albums.pictures.detail


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.techvista.etisalat.R
import com.techvista.etisalat.presentation.albums.AlbumViewModel
import com.techvista.etisalat.presentation.common.ExtrasData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PictureDetailScreen(
    navController: NavController,
    viewModel: AlbumViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        navController.currentBackStackEntry.let {
            it?.arguments?.takeIf {
                it.containsKey(ExtrasData.PICTURE_ID) && it.containsKey(ExtrasData.ALBUM_ID)
            }?.apply {
                viewModel.getPictureDetails(
                    this.getInt(ExtrasData.ALBUM_ID),
                    this.getInt(ExtrasData.PICTURE_ID)
                )?.let {
                    Row(
                        modifier = Modifier.padding(
                            20.dp, 10.dp, 5.dp, 5.dp
                        )
                    ) {
                        Text(
                            text = "Title: ${it.title}",
                            color = colorResource(id = R.color.textWhite),
                            fontStyle = FontStyle.Normal,
                            fontSize = 17.sp
                        )

                    }
                    Row(
                        modifier = Modifier.padding(
                            20.dp, 5.dp, 5.dp, 5.dp
                        )
                    ) {
                        Text(
                            text = "Url: ${it.url} ",
                            color = colorResource(id = R.color.textWhite),
                            fontStyle = FontStyle.Normal,
                            fontSize = 17.sp
                        )
                    }
                }
            }
        }
    }
}