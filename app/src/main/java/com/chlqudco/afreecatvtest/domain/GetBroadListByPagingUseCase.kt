package com.chlqudco.afreecatvtest.domain

import androidx.paging.PagingData
import com.chlqudco.afreecatvtest.data.repository.AppRepository
import com.chlqudco.afreecatvtest.data.response.Broad
import kotlinx.coroutines.flow.Flow

internal class GetBroadListByPagingUseCase(
    private val appRepository: AppRepository
): Usecase {

    suspend operator fun invoke(cate: String) : Flow<PagingData<Broad>>{
        return appRepository.getBroadListByPaging(cate)
    }
}