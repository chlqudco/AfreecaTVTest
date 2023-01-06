package com.chlqudco.afreecatvtest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chlqudco.afreecatvtest.data.network.ApiService
import com.chlqudco.afreecatvtest.data.response.Broad
import com.chlqudco.afreecatvtest.data.response.CategoryListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AppRepositoryImpl(
    private val ApiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : AppRepository {

    //페이징으로 방송 리스트 불러오기
    override suspend fun getBroadListByPaging(cate: String): Flow<PagingData<Broad>> {
        val pagingSourceFactory = {BroadListPagingSource(ApiService, cate)}

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                maxSize = 60
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }


    //방송 카테고리 정보 불러오기
    override suspend fun getCategoryList(): CategoryListResponse? = withContext(ioDispatcher){
        try {
            val response = ApiService.getCategoryList()
            return@withContext if (response.isSuccessful){
                response.body()
            } else {
                null
            }
        } catch (e: Exception){
            return@withContext null
        }
    }


}