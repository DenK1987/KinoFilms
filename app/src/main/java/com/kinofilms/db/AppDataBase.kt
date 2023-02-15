package com.kinofilms.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kinofilms.models.Movie
import com.kinofilms.utils.TypeConverter

@Database(entities = [Movie::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}