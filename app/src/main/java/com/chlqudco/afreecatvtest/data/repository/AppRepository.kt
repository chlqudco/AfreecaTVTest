package com.chlqudco.afreecatvtest.data.repository

import com.chlqudco.afreecatvtest.data.response.BroadCastListResponse
import com.chlqudco.afreecatvtest.data.response.CategoryListResponse

interface AppRepository {

    suspend fun getBroadcastList(cate: String): BroadCastListResponse?

    suspend fun getCategoryList(): CategoryListResponse?

}