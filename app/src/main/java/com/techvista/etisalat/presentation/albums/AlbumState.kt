package com.techvista.etisalat.presentation.albums

import com.techvista.etisalat.domain.model.Photos

data class AlbumState(
    val isLoading: Boolean = false,
    val listPhotos: Map<Int, List<Photos>> = mapOf(),
    val error: String = ""
)

