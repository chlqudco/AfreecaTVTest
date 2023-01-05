package com.chlqudco.afreecatvtest.data.repository

import android.util.Log
import com.chlqudco.afreecatvtest.data.network.ApiService
import com.chlqudco.afreecatvtest.data.response.BroadCastListResponse
import com.chlqudco.afreecatvtest.data.response.CategoryListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AppRepositoryImpl(
    private val ApiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : AppRepository {

    override suspend fun getBroadcastList(cate: String): BroadCastListResponse? = withContext(ioDispatcher){
        try {
            val response = ApiService.getBroadCastList(cate)
            return@withContext if (response.isSuccessful){
                response.body()
            } else{
                null
            }
        } catch (e: Exception){
            return@withContext null
        }
    }

    override suspend fun getCategoryList(): CategoryListResponse? = withContext(ioDispatcher){
        try {
            val response = ApiService.getCategoryList()
            return@withContext if (response.isSuccessful){
                Log.e("asdasd",response.body()?.broadCategory.toString())
                response.body()
            } else {
                Log.e("asdasd","bbb")
                null
            }
        } catch (e: Exception){
            return@withContext null
        }
    }

}