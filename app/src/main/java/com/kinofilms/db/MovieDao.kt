package com.kinofilms.db

import androidx.room.*
import com.kinofilms.models.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_DB")
    suspend fun getFavoriteMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)
}