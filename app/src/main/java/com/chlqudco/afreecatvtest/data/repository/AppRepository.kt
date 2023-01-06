package com.chlqudco.afreecatvtest.data.repository

import com.chlqudco.afreecatvtest.data.response.BroadListResponse
import com.chlqudco.afreecatvtest.data.response.CategoryListResponse

interface AppRepository {

    suspend fun getBroadList(cate: String): BroadListResponse?

    suspend fun getCategoryList(): CategoryListResponse?

}