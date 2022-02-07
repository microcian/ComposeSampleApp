package com.techvista.etisalat.commom

sealed class Screens(val route: String) {
    object AlbumsScreen : Screens("albums")
    object PictureScreen : Screens("pictures")// albumId
    object PictureDetailScreen : Screens("pictureDetail") // albumId,pictureId
}
