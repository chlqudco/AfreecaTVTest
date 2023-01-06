package com.chlqudco.afreecatvtest.presentation.broadlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.afreecatvtest.domain.GetBroadListUseCase
import com.chlqudco.afreecatvtest.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class BroadListViewModel(
    private val getBroadListUseCase: GetBroadListUseCase
) : BaseViewModel() {

    private var _broadCastListLiveData = MutableLiveData<BroadListState>(BroadListState.UnInitialized)
    val broadCastListLiveData: LiveData<BroadListState> = _broadCastListLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _broadCastListLiveData.postValue(BroadListState.Loading)
    }

    fun getBroadCastList(cate: String){
        viewModelScope.launch {
            val response = getBroadListUseCase(cate)
            if (response == null) {
                _broadCastListLiveData.postValue(BroadListState.Error)
            } else{
                _broadCastListLiveData.postValue(BroadListState.Success(response))
            }
        }
    }
}