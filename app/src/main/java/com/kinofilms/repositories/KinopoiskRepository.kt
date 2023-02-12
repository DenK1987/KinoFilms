package com.kinofilms.repositories

import android.util.Log
import com.kinofilms.models.AllMoviesCatalog
import com.kinofilms.network.KinopoiskApi
import com.kinofilms.network.models.MovieResponse
import com.kinofilms.utils.toListMoviesCatalog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

private const val NETWORK_ERROR = "Ошибка загрузки данных! \nПроверьте подключение к интернету!"
private const val NULL_ERROR = "Что-то пошло не так! Повторите попытку!"

class KinopoiskRepository @Inject constructor(
    private val api: KinopoiskApi
) {

    fun getAllMovies(page: Int, limit: Int): Flow<AllMoviesCatalog> {
        return flow {
            emit(
                AllMoviesCatalog(
                    data = api.getAllMovies(page, limit).checkError().body()?.docs?.toListMoviesCatalog()
                )
            )
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMoviesCatalog(emptyList(), NETWORK_ERROR)
                else -> AllMoviesCatalog(emptyList(), NULL_ERROR)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllMovies(): Flow<AllMoviesCatalog> {
        return flow {
            emit(
                AllMoviesCatalog(
                    data = api.getAllMovies().checkError().body()?.docs?.toListMoviesCatalog()
                )
            )
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMoviesCatalog(emptyList(), NETWORK_ERROR)
                else -> AllMoviesCatalog(emptyList(), NULL_ERROR)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllSerials(): Flow<AllMoviesCatalog> {
        return flow {
            emit(
                AllMoviesCatalog(
                    data = api.getAllSerials().checkError().body()?.docs?.toListMoviesCatalog()
                )
            )
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMoviesCatalog(emptyList(), NETWORK_ERROR)
                else -> AllMoviesCatalog(emptyList(), NULL_ERROR)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllCartoons(): Flow<AllMoviesCatalog> {
        return flow {
            emit(
                AllMoviesCatalog(
                    data = api.getAllCartoons().checkError().body()?.docs?.toListMoviesCatalog()
                )
            )
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMoviesCatalog(emptyList(), NETWORK_ERROR)
                else -> AllMoviesCatalog(emptyList(), NULL_ERROR)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllAnime(): Flow<AllMoviesCatalog> {
        return flow {
            emit(
                AllMoviesCatalog(
                    data = api.getAllAnime().checkError().body()?.docs?.toListMoviesCatalog()
                )
            )
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMoviesCatalog(emptyList(), NETWORK_ERROR)
                else -> AllMoviesCatalog(emptyList(), NULL_ERROR)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllAnimatedSeries(): Flow<AllMoviesCatalog> {
        return flow {
            emit(
                AllMoviesCatalog(
                    data = api.getAllAnimatedSeries().checkError()
                        .body()?.docs?.toListMoviesCatalog()
                )
            )
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMoviesCatalog(emptyList(), NETWORK_ERROR)
                else -> AllMoviesCatalog(emptyList(), NULL_ERROR)
            }
        }.flowOn(Dispatchers.IO)
    }

//    fun getMovie(id: Int): Flow<Movie?> {
//        return flow {
//            emit(api.getMovie(id).checkError().body()?.toMovie())
//        }.catch { throwable ->
//            when (throwable) {
//                is HttpException -> AllMovies(emptyList(), NETWORK_ERROR)
//                else -> AllMovies(emptyList(), NULL_ERROR)
//            }
//        }.flowOn(Dispatchers.IO)
//    }

    suspend fun getMovie(id: Int): Response<MovieResponse> {
//        Log.e("KEK", "KinopoiskRepository $id")
        return api.getMovie(id)
    }
}

fun <T> Response<T>.checkError(): Response<T> {
    if (!this.isSuccessful) throw HttpException(this)
    if (this.body() == null) throw NullPointerException()
    return this
}