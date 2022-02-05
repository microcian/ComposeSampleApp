package com.techvista.etisalat.domain.use_case

import com.techvista.etisalat.commom.Response
import com.techvista.etisalat.data.repository.PhotosDataRepository
import com.techvista.etisalat.domain.model.Photos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: PhotosDataRepository
) {

    operator fun invoke(): Flow<Response<List<Photos>>> = flow {
        try {
            emit(Response.Loading<List<Photos>>())
            val photos = repository.getPhotos()
            emit(Response.Success(photos))
        } catch (e: HttpException) {
            emit(Response.Error<List<Photos>>(e.localizedMessage))
        } catch (e: IOException) {
            emit(Response.Error<List<Photos>>("Could not reach server, Check internet connection"))
        }
    }
}