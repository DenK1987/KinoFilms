package com.kinofilms.network

import com.kinofilms.network.models.AllMoviesResponse
import com.kinofilms.network.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskApi {

//    @GET("/movie")
//    suspend fun getMovies(
//        @Query("page") page: Int,
//        @Query("limit") limit: Int? = 50              limit=820854
//    ): Response<AllDisneyHeroesResponse>

    @GET("/movie?field=typeNumber&search=1&limit=300&token=4PY136E-954MAHZ-NGHVQS4-QPAS8PA")
    suspend fun getAllMovies(): Response<AllMoviesResponse>

    @GET("/movie?field=typeNumber&search=2&limit=300&token=4PY136E-954MAHZ-NGHVQS4-QPAS8PA")
    suspend fun getAllSerials(): Response<AllMoviesResponse>

    @GET("/movie?field=typeNumber&search=3&limit=300&token=4PY136E-954MAHZ-NGHVQS4-QPAS8PA")
    suspend fun getAllCartoons(): Response<AllMoviesResponse>

    @GET("/movie?field=typeNumber&search=4&limit=300&token=4PY136E-954MAHZ-NGHVQS4-QPAS8PA")
    suspend fun getAllAnime(): Response<AllMoviesResponse>

    @GET("/movie?field=typeNumber&search=5&limit=300&token=4PY136E-954MAHZ-NGHVQS4-QPAS8PA")
    suspend fun getAllAnimatedSeries(): Response<AllMoviesResponse>

//    @GET("/movie/{id}")
//    suspend fun getMovie(@Path("id") id: Int): Response<MovieResponse>

    @GET("/movie?field=id&token=4PY136E-954MAHZ-NGHVQS4-QPAS8PA")
    suspend fun getMovie(@Query("search") id: Int): Response<MovieResponse>

    @GET("/movie/{id}")
    suspend fun getMovie2(@Path("id") id: Int): Response<MovieResponse>

}