package com.kinofilms.repositories

import android.util.Log
import com.kinofilms.models.AllMovies
import com.kinofilms.models.Movie
import com.kinofilms.network.KinopoiskApi
import com.kinofilms.network.models.MovieResponse
import com.kinofilms.utils.toListMovies
import com.kinofilms.utils.toMovie
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

    fun getAllMovies(): Flow<AllMovies> {
        return flow {
            emit(AllMovies(data = api.getAllMovies().checkError().body()?.docs?.toListMovies()))
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMovies(emptyList(), NETWORK_ERROR)
                else -> AllMovies(emptyList(), NULL_ERROR)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllSerials(): Flow<AllMovies> {
        return flow {
            emit(AllMovies(data = api.getAllSerials().checkError().body()?.docs?.toListMovies()))
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMovies(emptyList(), NETWORK_ERROR)
                else -> AllMovies(emptyList(), NULL_ERROR)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllCartoons(): Flow<AllMovies> {
        return flow {
            emit(AllMovies(data = api.getAllCartoons().checkError().body()?.docs?.toListMovies()))
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMovies(emptyList(), NETWORK_ERROR)
                else -> AllMovies(emptyList(), NULL_ERROR)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllAnime(): Flow<AllMovies> {
        return flow {
            emit(AllMovies(data = api.getAllAnime().checkError().body()?.docs?.toListMovies()))
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMovies(emptyList(), NETWORK_ERROR)
                else -> AllMovies(emptyList(), NULL_ERROR)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllAnimatedSeries(): Flow<AllMovies> {
        return flow {
            emit(
                AllMovies(
                    data = api.getAllAnimatedSeries().checkError().body()?.docs?.toListMovies()
                )
            )
        }.catch { throwable ->
            when (throwable) {
                is HttpException -> AllMovies(emptyList(), NETWORK_ERROR)
                else -> AllMovies(emptyList(), NULL_ERROR)
            }
        }.flowOn(Dispatchers.IO)
    }

//    fun getMovie(id: Int): Flow<Movie> {
//        return flow {
//            emit(Movie(api.getMovie(id).checkError().body()?.id ?: 0))
//        }
//    }

    suspend fun getMovie(id: Int): Response<MovieResponse> {
        Log.e("KEK", "KinopoiskRepository $id")
        return api.getMovie(id)
    }


}

fun <T> Response<T>.checkError(): Response<T> {
    if (!this.isSuccessful) throw HttpException(this)
    if (this.body() == null) throw NullPointerException()
    return this
}