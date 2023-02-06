package com.kinofilms.network

import com.kinofilms.network.models.moviesbytype.MoviesByType
import retrofit2.Response
import retrofit2.http.GET

interface KinopoiskApi {

//    @GET("/movie")
//    suspend fun getMovies(
//        @Query("page") page: Int,
//        @Query("limit") limit: Int? = 50              limit=820854
//    ): Response<AllDisneyHeroesResponse>

//    @GET("/movie?field=name&search=&limit=100&token=4PY136E-954MAHZ-NGHVQS4-QPAS8PA")
//    suspend fun getAllMovies(): Response<Model2>

    @GET("/movie?field=typeNumber&search=1&limit=200&token=4PY136E-954MAHZ-NGHVQS4-QPAS8PA")
    suspend fun getAllMovies(): Response<MoviesByType>


//    @GET("/characters/{id}")
//    suspend fun getImageDisneyHero(@Path("id") id: String): Response<DisneyHeroResponse>

}