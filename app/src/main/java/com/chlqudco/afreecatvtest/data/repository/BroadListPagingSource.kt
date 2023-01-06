package com.chlqudco.afreecatvtest.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chlqudco.afreecatvtest.data.network.ApiService
import com.chlqudco.afreecatvtest.data.response.Broad

class BroadListPagingSource(
    private val apiService: ApiService,
    private val cate: String
) : PagingSource<Int, Broad>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Broad> {
        return try {
            val position = params.key ?: 1

            val response = apiService.getBroadList(position, cate).body()!!

            //20개씩 불러오기
            val nextKey = if(response.pageNo * 20 > response.totalCnt){
                null
            } else {
                position + (params.loadSize / 20)
            }

            LoadResult.Page(
                data = response.broad,
                prevKey = if (position == 1) null else position -1,
                nextKey = nextKey
            )

        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Broad>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}