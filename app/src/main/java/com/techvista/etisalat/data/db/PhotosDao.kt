package com.techvista.etisalat.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.techvista.etisalat.domain.model.Photos

@Dao
interface PhotosDao {
    @Query("SELECT * FROM photos")
    suspend fun getPhotos(): List<Photos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(listPhotos: List<Photos>)
}