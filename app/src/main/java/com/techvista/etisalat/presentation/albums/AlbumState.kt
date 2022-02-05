package com.techvista.etisalat.presentation.albums

import com.techvista.etisalat.domain.model.Photos

data class AlbumState (
    val isLoading: Boolean = false,
    val listPhotos: List<Photos> = emptyList(),
    val error: String = ""

)

