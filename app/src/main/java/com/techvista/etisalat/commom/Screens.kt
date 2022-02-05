package com.techvista.etisalat.commom

sealed class Screens(val route: String) {
    object AlbumsScreen: Screens("albums")
}
