package com.chlqudco.afreecatvtest.data.network

import com.chlqudco.afreecatvtest.BuildConfig
import com.chlqudco.afreecatvtest.data.response.BroadListResponse
import com.chlqudco.afreecatvtest.data.response.CategoryListResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/broad/list?client_id=${BuildConfig.client_id}&select_key=cate")
    suspend fun getBroadList(
        @Query("select_value") select_value: String
    ): Response<BroadListResponse>

    @GET("broad/category/list?client_id=${BuildConfig.client_id}")
    suspend fun getCategoryList(): Response<CategoryListResponse>
}