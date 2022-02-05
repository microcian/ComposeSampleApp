package com.techvista.etisalat.data.repository

import com.techvista.etisalat.commom.AppDataFactory
import com.techvista.etisalat.domain.model.Photos
import com.techvista.etisalat.domain.repository.PhotosRepository

class PhotosDataRepository (
    private val factory: AppDataFactory
): PhotosRepository {

    private fun getDataSource(): PhotosRepository {
        return factory.getRepository()
    }

    override suspend fun getPhotos(): List<Photos> {
        return getDataSource().getPhotos()
    }
}