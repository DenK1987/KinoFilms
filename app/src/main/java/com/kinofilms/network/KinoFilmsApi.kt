package com.kinofilms.network

import com.kinofilms.network.models.AllMoviesCatalogResponse
import com.kinofilms.network.models.MovieResponse
import com.kinofilms.network.models.PersonModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KinoFilmsApi {

//    @GET("/movie?field=typeNumber&search=1&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
//    suspend fun getAllMovies(
//        @Query("page") page: Int,
//        @Query("limit") limit: Int? = 50               // limit=820854
//    ): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=1&limit=100&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllMovies(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=2&limit=100&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllSerials(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=3&limit=100&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllCartoons(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=4&limit=100&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllAnime(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=5&limit=100&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllAnimatedSeries(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=id&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getMovie(@Query("search") id: Int): Response<MovieResponse>

    @GET("/movie?field=typeNumber&search=1&sortField=votes.imdb&sortType=-1&limit=50&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllPopularForeignMovies(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=2&sortField=votes.imdb&sortType=-1&limit=50&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllPopularForeignSerials(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=1&field=countries.name&search=Россия&sortField=votes.kp&sortType=-1&limit=50&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllPopularRussianMovies(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=2&field=countries.name&search=Россия&sortField=votes.kp&sortType=-1&limit=50&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllPopularRussianSerials(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=3&sortField=votes.imdb&sortType=-1&limit=50&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllPopularCartoons(): Response<AllMoviesCatalogResponse>

    @GET("/person?field=id&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getPerson(@Query("search") id: Int): Response<PersonModelResponse>
}