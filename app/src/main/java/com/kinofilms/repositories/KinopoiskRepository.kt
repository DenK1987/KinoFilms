package com.kinofilms.repositories

import android.util.Log
import com.kinofilms.models.AllMovies
import com.kinofilms.network.KinopoiskApi
import com.kinofilms.utils.toListMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class KinopoiskRepository @Inject constructor(
    private val api: KinopoiskApi
) {

//    suspend fun getAllMovies(): Response<Model2> {
//        return api.getAllMovies()
//    }

//    fun getAllMovies(): Flow<Movie> {
//        return api.getAllMovies()
//    }

    fun getAllMovies(): Flow<AllMovies> {

        return flow {


            emit(
                AllMovies(
                    data = api.getAllMovies().checkError().body()?.docs?.toListMovie()

                )
            )
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMovies(emptyList(), "error")
                else -> AllMovies(emptyList(), "Что то не так")
            }


        }.flowOn(Dispatchers.IO)
    }

}

fun <T> Response<T>.checkError(): Response<T> {
    if (!this.isSuccessful) throw HttpException(this)
    if (this.body() == null) throw NullPointerException()
    return this
}