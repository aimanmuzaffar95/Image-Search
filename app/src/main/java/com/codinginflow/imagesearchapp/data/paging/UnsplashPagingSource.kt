package com.codinginflow.imagesearchapp.data.paging

import androidx.paging.PagingSource
import com.codinginflow.imagesearchapp.api.UnsplashApi
import com.codinginflow.imagesearchapp.models.UnsplashPhoto
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

const val STARTING_INDEX = 1

class UnsplashPagingSource(
        private val unsplashApi: UnsplashApi,
        private val query: String
) : PagingSource<Int, UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: STARTING_INDEX

        return try {
            val response = unsplashApi.searchPhotos(query, position, params.loadSize)
            val photos = response.results

            LoadResult.Page(
                    data = photos,
                    prevKey = if (position == STARTING_INDEX) null else position - 1,
                    nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}