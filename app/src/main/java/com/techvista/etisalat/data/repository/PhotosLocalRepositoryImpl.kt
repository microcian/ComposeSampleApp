package com.techvista.etisalat.data.repository

import com.techvista.etisalat.data.db.PhotosDao
import com.techvista.etisalat.domain.model.Photos
import com.techvista.etisalat.domain.repository.PhotosRepository

class PhotosLocalRepositoryImpl(
    private val photosDao: PhotosDao
) : PhotosRepository {

    override suspend fun getPhotos(): List<Photos> {
        return photosDao.getPhotos()
    }
}