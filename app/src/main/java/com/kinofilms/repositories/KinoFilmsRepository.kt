package com.kinofilms.repositories

import com.kinofilms.db.MovieDao
import com.kinofilms.models.AllMoviesCatalog
import com.kinofilms.models.Movie
import com.kinofilms.network.KinoFilmsApi
import com.kinofilms.network.models.AllMoviesCatalogResponse
import com.kinofilms.network.models.MovieResponse
import com.kinofilms.network.models.PersonModelResponse
import com.kinofilms.ui.recommendations.RecommendationType.*
import com.kinofilms.utils.toListMoviesCatalog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

private const val NETWORK_ERROR = "Ошибка загрузки данных! \nПроверьте подключение к интернету!"
private const val NULL_ERROR = "Что-то пошло не так! Повторите попытку!"

class KinoFilmsRepository @Inject constructor(
    private val api: KinoFilmsApi,
    private val movieDao: MovieDao
) {

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

    suspend fun getMovie(id: Int): Response<MovieResponse> {
        return api.getMovie(id)
    }

    fun getAllPopulars(): Flow<Map<String, AllMoviesCatalogResponse>> {
        return flow {

            val result = mutableMapOf<String, AllMoviesCatalogResponse>()

            if (api.getAllPopularForeignMovies().isSuccessful)
                api.getAllPopularForeignMovies().body()?.let { data ->
                    result.put(POPULAR_FOREIGN_MOVIES.name, data)
                }
            else emptyList<AllMoviesCatalogResponse>()

            if (api.getAllPopularForeignSerials().isSuccessful)
                api.getAllPopularForeignSerials().body()?.let { model ->
                    result.put(POPULAR_FOREIGN_SERIALS.name, model)
                }
            else emptyList<AllMoviesCatalogResponse>()

            if (api.getAllPopularRussianMovies().isSuccessful)
                api.getAllPopularRussianMovies().body()?.let { model ->
                    result.put(POPULAR_RUSSIAN_MOVIES.name, model)
                }
            else emptyList<AllMoviesCatalogResponse>()

            if (api.getAllPopularRussianSerials().isSuccessful)
                api.getAllPopularRussianSerials().body()?.let { model ->
                    result.put(POPULAR_RUSSIAN_SERIALS.name, model)
                }
            else emptyList<AllMoviesCatalogResponse>()

            if (api.getAllPopularCartoons().isSuccessful)
                api.getAllPopularCartoons().body()?.let { model ->
                    result.put(POPULAR_CARTOONS.name, model)
                }
            else emptyList<AllMoviesCatalogResponse>()

            emit(result)
        }.catch {
            emit(emptyMap<String, AllMoviesCatalogResponse>().toMutableMap())
        }.flowOn(Dispatchers.IO)
    }

//    suspend fun getAllPopularForeignMovies(): Response<AllMoviesCatalogResponse> {
//        return api.getAllPopularForeignMovies()
//    }
//
//    suspend fun getAllPopularForeignSerials(): Response<AllMoviesCatalogResponse> {
//        return api.getAllPopularForeignSerials()
//    }
//
//    suspend fun getAllPopularRussianMovies(): Response<AllMoviesCatalogResponse> {
//        return api.getAllPopularRussianMovies()
//    }
//
//    suspend fun getAllPopularRussianSerials(): Response<AllMoviesCatalogResponse> {
//        return api.getAllPopularRussianSerials()
//    }
//
//    suspend fun getAllPopularCartoons(): Response<AllMoviesCatalogResponse> {
//        return api.getAllPopularCartoons()
//    }

    suspend fun getFavoriteMovies(): List<Movie> {
        return movieDao.getFavoriteMovies()
    }

    suspend fun addMovieToFavorite(movie: Movie) {
        movieDao.addMovie(movie)
    }

    suspend fun deleteMovieFromFavorite(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

    suspend fun getPerson(id: Int): Response<PersonModelResponse> {
        return api.getPerson(id)
    }
}

fun <T> Response<T>.checkError(): Response<T> {
    if (!this.isSuccessful) throw HttpException(this)
    if (this.body() == null) throw NullPointerException()
    return this
}