package com.techvista.etisalat.data.remote

import com.techvista.etisalat.data.remote.EndPointConstants.END_POINT_PHOTOS
import com.techvista.etisalat.domain.model.Photos
import retrofit2.http.GET


interface RemoteApi {
    @GET(END_POINT_PHOTOS)
    suspend fun getPhotos(): List<Photos>
}