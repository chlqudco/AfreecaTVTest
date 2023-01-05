package com.chlqudco.afreecatvtest.domain

import com.chlqudco.afreecatvtest.data.repository.AppRepository
import com.chlqudco.afreecatvtest.data.response.BroadCastListResponse

internal class GetBroadCastListUseCase(
    private val appRepository: AppRepository
) : Usecase{

    suspend operator fun invoke(cate: String): BroadCastListResponse?{
        return appRepository.getBroadcastList(cate)
    }
}