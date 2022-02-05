package com.techvista.etisalat.di

import android.app.Application
import android.provider.SyncStateContract
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor

import com.techvista.etisalat.BuildConfig
import com.techvista.etisalat.commom.AppDataFactory
import com.techvista.etisalat.data.db.AppDatabase
import com.techvista.etisalat.data.remote.EndPointConstants.BASE_URL
import com.techvista.etisalat.data.remote.RemoteApi
import com.techvista.etisalat.data.repository.PhotosDataRepository
import com.techvista.etisalat.data.repository.PhotosLocalRepositoryImpl
import com.techvista.etisalat.data.repository.PhotosRemoteRepositoryImpl
import com.techvista.etisalat.domain.repository.PhotosRepository
import java.util.concurrent.TimeUnit
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRemoteApi(): RemoteApi {
        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(5, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
            builder.addInterceptor(interceptor)
        }


        val client: OkHttpClient = builder.build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RemoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .build();
    }

    @Provides
    @Singleton
    @Named("remote")
    fun providePhotosRemoteRepository(api: RemoteApi, db: AppDatabase): PhotosRepository {
        return PhotosRemoteRepositoryImpl(api, db.photosDao)
    }

    @Provides
    @Singleton
    @Named("local")
    fun providePhotosLocalRepository(db: AppDatabase): PhotosRepository {
        return PhotosLocalRepositoryImpl(db.photosDao);
    }

    @Provides
    @Singleton
    fun providePhotosDataFactory(
        app: Application,
        @Named("local") local: PhotosRepository,
        @Named("remote") remote: PhotosRepository
    ): AppDataFactory {
        return AppDataFactory(app, local, remote)
    }

    @Provides
    @Singleton
    fun providePhotosDataRepository(factory: AppDataFactory): PhotosDataRepository {
        return PhotosDataRepository(factory)
    }
}