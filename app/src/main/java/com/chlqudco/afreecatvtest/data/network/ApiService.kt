package com.chlqudco.afreecatvtest.data.network

import com.chlqudco.afreecatvtest.data.response.BroadCastListResponse
import com.chlqudco.afreecatvtest.data.response.CategoryListResponse
import com.chlqudco.afreecatvtest.presentation.utility.AppKey
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/broad/list?client_id=${AppKey.CLIENT_ID}&select_key=cate")
    suspend fun getBroadCastList(
        @Query("select_value") select_value: String
    ): Response<BroadCastListResponse>

    @GET("broad/category/list?client_id=${AppKey.CLIENT_ID}")
    suspend fun getCategoryList(): Response<CategoryListResponse>
}