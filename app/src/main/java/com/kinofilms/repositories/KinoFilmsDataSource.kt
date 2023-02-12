package com.kinofilms.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kinofilms.models.MovieCatalog
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

//class KinoFilmsDataSource @Inject constructor(
//    private val repository: KinopoiskRepository
//) : PagingSource<Int, MovieCatalog>() {
//
//    override fun getRefreshKey(state: PagingState<Int, MovieCatalog>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieCatalog> {
//        return try {
//            val pageNumber = params.key ?: 1
//            val response = repository.getAllMovies(pageNumber, params.loadSize)
//            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
//            val nextKey = if (response.body()?.data?.isNotEmpty() == true) pageNumber + 1 else null
//            return LoadResult.Page(
//                data = response.body()?.data?.toListDisneyHero() ?: emptyList(),
//                prevKey = prevKey,
//                nextKey = nextKey
//            )
//        } catch (e: IOException) {
//            LoadResult.Error(e)
//        } catch (e: HttpException) {
//            LoadResult.Error(e)
//        }
//    }
//}

