package com.kinofilms.di

import android.content.Context
import androidx.room.Room
import com.kinofilms.db.AppDataBase
import com.kinofilms.db.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun provideDB(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java, "database-name"
        ).build()
    }

    @Provides
    fun provideMovieDao(appDataBase: AppDataBase): MovieDao {
        return appDataBase.movieDao()
    }
}