package com.techvista.etisalat.commom

import android.content.Context
import com.techvista.etisalat.commom.utils.NetworkUtils
import com.techvista.etisalat.domain.repository.PhotosRepository

class AppDataFactory(
    val app: Context,
    private val localRepository: PhotosRepository,
    private val remoteRepository: PhotosRepository,
) {
    fun getRepository(): PhotosRepository {
        return if (!NetworkUtils.isNetworkAvailable(app))
            localRepository
        else
            remoteRepository
    }
}

