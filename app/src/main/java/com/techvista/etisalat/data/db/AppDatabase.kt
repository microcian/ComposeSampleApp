package com.techvista.etisalat.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techvista.etisalat.domain.model.Photos

@Database(
    entities = [Photos::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract val photosDao: PhotosDao

    companion object {
        const val DATABASE_NAME = "techvista_photos_db"
    }
}