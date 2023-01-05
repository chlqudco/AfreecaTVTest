package com.chlqudco.afreecatvtest.presentation.main

import com.chlqudco.afreecatvtest.data.response.CategoryListResponse

internal sealed class MainState {

    object UnInitialized: MainState()

    object Loading: MainState()

    data class Success(
        val list: CategoryListResponse
    ): MainState()

    object Error: MainState()
}