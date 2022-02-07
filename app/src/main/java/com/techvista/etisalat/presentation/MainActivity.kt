package com.techvista.etisalat.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.techvista.etisalat.commom.Screens
import com.techvista.etisalat.presentation.albums.AlbumScreen
import com.techvista.etisalat.presentation.albums.AlbumViewModel
import com.techvista.etisalat.presentation.albums.pictures.PictureScreen
import com.techvista.etisalat.presentation.albums.pictures.detail.PictureDetailScreen
import com.techvista.etisalat.presentation.common.ExtrasData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screens.AlbumsScreen.route
            ) {
                composable(
                    route = Screens.AlbumsScreen.route
                ) {
                    AlbumScreen(navController = navController, viewModel = viewModel)
                }
                composable(
                    route = Screens.PictureScreen.route + "/{${ExtrasData.ALBUM_ID}}",
                    arguments = listOf(navArgument(ExtrasData.ALBUM_ID) { type = NavType.IntType })

                ) {
                    PictureScreen(navController = navController, viewModel = viewModel)
                }
                composable(
                    route = Screens.PictureDetailScreen.route + "/{${ExtrasData.ALBUM_ID}}"+ "/{${ExtrasData.PICTURE_ID}}",
                    arguments = listOf(
                        navArgument(ExtrasData.PICTURE_ID) { type = NavType.IntType },
                        navArgument(ExtrasData.ALBUM_ID) { type = NavType.IntType })

                ) {
                    PictureDetailScreen(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}