package com.techvista.etisalat.presentation.albums

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techvista.etisalat.commom.Response
import com.techvista.etisalat.domain.model.Photos
import com.techvista.etisalat.domain.use_case.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {
    private val _state = mutableStateOf(AlbumState())
    val state: State<AlbumState> = _state

    init {
        getPhotos()
    }

    private fun getPhotos() {
        getPhotosUseCase().onEach { response ->
            when (response) {
                is Response.Success -> {
                    response.data?.groupBy {
                        it.albumId
                    }?.apply {
                        _state.value = AlbumState(listPhotos = this)
                    }
                }

                is Response.Loading -> {
                    _state.value = AlbumState(isLoading = true)
                }

                is Response.Error -> {
                    _state.value =
                        AlbumState(error = response.message ?: "An Unexpected error occured")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAlbumPictures(id: Int): List<Photos> {
        return state.value.listPhotos.get(id) ?: emptyList()
    }

    fun getPictureDetails(albumId: Int, pictureId: Int): Photos? {
        return state.value.listPhotos.get(albumId)?.find {
            it.id == pictureId
        }
    }
}