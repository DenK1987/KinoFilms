package com.kinofilms.network

import com.kinofilms.network.models.AllMoviesCatalogResponse
import com.kinofilms.network.models.MovieCatalogResponse
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

    @GET("/movie?field=typeNumber&search=1&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllMovies(
        @Query("page") page: Int,
        @Query("limit") limit: Int? = 50
    ): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=1&limit=300&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllMovies(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=2&limit=300&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllSerials(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=3&limit=300&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllCartoons(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=4&limit=300&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllAnime(): Response<AllMoviesCatalogResponse>

    @GET("/movie?field=typeNumber&search=5&limit=300&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getAllAnimatedSeries(): Response<AllMoviesCatalogResponse>

//    @GET("/movie/{id}")
//    suspend fun getMovie(@Path("id") id: Int): Response<MovieResponse>

    @GET("/movie?field=id&token=4A9G25B-SDCM3VY-G9AX8G2-J5CQTV2")
    suspend fun getMovie(@Query("search") id: Int): Response<MovieResponse>

}