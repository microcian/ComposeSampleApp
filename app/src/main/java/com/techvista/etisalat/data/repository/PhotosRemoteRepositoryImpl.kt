package com.techvista.etisalat.data.repository

import com.techvista.etisalat.data.db.PhotosDao
import com.techvista.etisalat.data.remote.RemoteApi
import com.techvista.etisalat.domain.model.Photos
import com.techvista.etisalat.domain.repository.PhotosRepository
import javax.inject.Inject

class PhotosRemoteRepositoryImpl @Inject constructor(
    private val api: RemoteApi,
    private val photosDao: PhotosDao
): PhotosRepository {

    override suspend fun getPhotos(): List<Photos> {
        insertPhotosToLocalDb(api.getPhotos())
        return api.getPhotos()
    }
    private suspend fun insertPhotosToLocalDb(listPhotos: List<Photos>) {
        photosDao.insertPhotos(listPhotos)
    }
}