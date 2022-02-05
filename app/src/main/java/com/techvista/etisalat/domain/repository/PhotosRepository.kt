package com.techvista.etisalat.domain.repository

import com.techvista.etisalat.domain.model.Photos

interface PhotosRepository {
    suspend fun getPhotos(): List<Photos>
}