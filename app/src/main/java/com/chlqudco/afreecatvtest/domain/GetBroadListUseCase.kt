package com.chlqudco.afreecatvtest.domain

import com.chlqudco.afreecatvtest.data.repository.AppRepository
import com.chlqudco.afreecatvtest.data.response.BroadListResponse

internal class GetBroadListUseCase(
    private val appRepository: AppRepository
) : Usecase{

    suspend operator fun invoke(cate: String): BroadListResponse?{
        return appRepository.getBroadList(cate)
    }
}