package com.kinofilms.repositories

import com.kinofilms.models.AllMoviesCatalog
import com.kinofilms.network.KinoFilmsApi
import com.kinofilms.network.models.AllMoviesCatalogResponse
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

class KinoFilmsRepository @Inject constructor(
    private val api: KinoFilmsApi
) {

//    fun getAllMovies(page: Int, limit: Int): Flow<AllMoviesCatalog> {
//        return flow {
//            emit(
//                AllMoviesCatalog(
//                    data = api.getAllMovies(page, limit).checkError().body()?.docs?.toListMoviesCatalog()
//                )
//            )
//        }.catch { throwable ->
//            when (throwable) {
//                is HttpException -> AllMoviesCatalog(emptyList(), NETWORK_ERROR)
//                else -> AllMoviesCatalog(emptyList(), NULL_ERROR)
//            }
//        }.flowOn(Dispatchers.IO)
//    }

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

//    fun getAllPopularForeignMovies(): Flow<AllMoviesCatalog> {
//        return flow {
//            emit(
//                AllMoviesCatalog(
//                    data = api.getAllMovies().checkError().body()?.docs?.toListMoviesCatalog()
//                )
//            )
//        }.catch { throwable ->
//            when (throwable) {
//                is HttpException -> AllMoviesCatalog(emptyList(), NETWORK_ERROR)
//                else -> AllMoviesCatalog(emptyList(), NULL_ERROR)
//            }
//        }.flowOn(Dispatchers.IO)
//    }


//
//    fun getAllMoviesSortedByPopularity(): Flow<AllMoviesCatalog> {
//        return flow {
//            emit(
//                AllMoviesCatalog(
//                    data = api.getAllMovies().checkError().body()?.docs?.toListMoviesCatalog()
//                        ?.sortedBy { movies ->
//                            movies.votesKp
//                        }
//                )
//            )
//        }.catch { throwable ->
//            when (throwable) {
//                is HttpException -> AllMoviesCatalog(emptyList(), NETWORK_ERROR)
//                else -> AllMoviesCatalog(emptyList(), NULL_ERROR)
//            }
//        }.flowOn(Dispatchers.IO)
//    }
//
//    fun getAllMoviesSortedByRating(): Flow<AllMoviesCatalog> {
//        return flow {
//            emit(
//                AllMoviesCatalog(
//                    data = api.getAllMovies().checkError().body()?.docs?.toListMoviesCatalog()
//                        ?.sortedByDescending { movies ->
//                            movies.ratingKp
//                        }
//                )
//            )
//        }.catch { throwable ->
//            when (throwable) {
//                is HttpException -> AllMoviesCatalog(emptyList(), NETWORK_ERROR)
//                else -> AllMoviesCatalog(emptyList(), NULL_ERROR)
//            }
//        }.flowOn(Dispatchers.IO)
//    }
//
//    fun getAllMoviesSortedByReleaseDate(): Flow<AllMoviesCatalog> {
//        return flow {
//            emit(
//                AllMoviesCatalog(
//                    data = api.getAllMovies().checkError().body()?.docs?.toListMoviesCatalog()
//                        ?.sortedByDescending { movies ->
//                            movies.year
//                        }
//                )
//            )
//        }.catch { throwable ->
//            when (throwable) {
//                is HttpException -> AllMoviesCatalog(emptyList(), NETWORK_ERROR)
//                else -> AllMoviesCatalog(emptyList(), NULL_ERROR)
//            }
//        }.flowOn(Dispatchers.IO)
//    }

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

    suspend fun getAllPopularForeignMovies(): Response<AllMoviesCatalogResponse> {
        return api.getAllPopularForeignMovies()
    }

    suspend fun getAllPopularForeignSerials(): Response<AllMoviesCatalogResponse> {
        return api.getAllPopularForeignSerials()
    }

    suspend fun getAllPopularRussianMovies(): Response<AllMoviesCatalogResponse> {
        return api.getAllPopularRussianMovies()
    }

    suspend fun getAllPopularRussianSerials(): Response<AllMoviesCatalogResponse> {
        return api.getAllPopularRussianSerials()
    }
}

fun <T> Response<T>.checkError(): Response<T> {
    if (!this.isSuccessful) throw HttpException(this)
    if (this.body() == null) throw NullPointerException()
    return this
}