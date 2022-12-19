package com.luisfagundes.domain.mockdata

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.luisfagundes.domain.models.Recipe

class PagingSourceFactory {
    fun create(recipes: List<Recipe>) = object : PagingSource<Int, Recipe>() {
        override fun getRefreshKey(state: PagingState<Int, Recipe>) = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
            return LoadResult.Page(
                data = recipes,
                prevKey = null,
                nextKey = 10
            )
        }
    }
}