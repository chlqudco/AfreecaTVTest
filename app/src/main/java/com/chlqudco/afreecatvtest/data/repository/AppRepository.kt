package com.chlqudco.afreecatvtest.data.repository

import androidx.paging.PagingData
import com.chlqudco.afreecatvtest.data.response.Broad
import com.chlqudco.afreecatvtest.data.response.CategoryListResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    //페이징으로 방송 리스트 불러오기
    suspend fun getBroadListByPaging(cate: String): Flow<PagingData<Broad>>

    //방송 카테고리 정보 불러오기
    suspend fun getCategoryList(): CategoryListResponse?

}