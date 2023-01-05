package com.chlqudco.afreecatvtest.domain

import com.chlqudco.afreecatvtest.data.repository.AppRepository
import com.chlqudco.afreecatvtest.data.response.CategoryListResponse

internal class GetCategoryListUseCase(
    private val appRepository: AppRepository
) : Usecase {

    suspend operator fun invoke(): CategoryListResponse?{
        return appRepository.getCategoryList()
    }
}