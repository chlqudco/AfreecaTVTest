package com.chlqudco.afreecatvtest.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.afreecatvtest.domain.GetCategoryListUseCase
import com.chlqudco.afreecatvtest.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class MainViewModel(
    private val getCategoryListUseCase: GetCategoryListUseCase
): BaseViewModel() {

    private var _categoryListLiveData = MutableLiveData<MainState>(MainState.UnInitialized)
    val categoryListLiveData: LiveData<MainState> = _categoryListLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _categoryListLiveData.postValue(MainState.Loading)
    }

    fun getCategoryList(){
        viewModelScope.launch {
            val response = getCategoryListUseCase()
            if (response == null) {
                _categoryListLiveData.postValue(MainState.Error)
            } else{
                _categoryListLiveData.postValue(MainState.Success(response))
            }
        }
    }
}