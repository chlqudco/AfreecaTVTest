package com.chlqudco.afreecatvtest.di

import com.chlqudco.afreecatvtest.data.network.buildOkHttpClient
import com.chlqudco.afreecatvtest.data.network.provideApiService
import com.chlqudco.afreecatvtest.data.network.provideGsonConverterFactory
import com.chlqudco.afreecatvtest.data.network.provideRetrofit
import com.chlqudco.afreecatvtest.data.repository.AppRepository
import com.chlqudco.afreecatvtest.data.repository.AppRepositoryImpl
import com.chlqudco.afreecatvtest.domain.GetBroadListUseCase
import com.chlqudco.afreecatvtest.domain.GetCategoryListUseCase
import com.chlqudco.afreecatvtest.presentation.detail.DetailViewModel
import com.chlqudco.afreecatvtest.presentation.main.MainViewModel
import com.chlqudco.afreecatvtest.presentation.broadlist.BroadListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    //코루틴
    single { Dispatchers.IO }
    single { Dispatchers.Main }

    //뷰모델
    viewModel { MainViewModel(get()) }
    viewModel { BroadListViewModel(get()) }
    viewModel { DetailViewModel() }

    //retrofit
    single { provideGsonConverterFactory() }
    single { buildOkHttpClient() }
    single { provideApiService(get()) }
    single { provideRetrofit(get(), get()) }

    //repository
    single<AppRepository> { AppRepositoryImpl(get(), get()) }

    //UseCase
    factory { GetBroadListUseCase(get()) }
    factory { GetCategoryListUseCase(get()) }
}