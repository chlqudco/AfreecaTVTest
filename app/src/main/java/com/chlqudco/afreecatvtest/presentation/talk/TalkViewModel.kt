package com.chlqudco.afreecatvtest.presentation.talk

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chlqudco.afreecatvtest.domain.GetBroadCastListUseCase
import com.chlqudco.afreecatvtest.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class TalkViewModel(
    private val getBroadCastListUseCase: GetBroadCastListUseCase
) : BaseViewModel() {

    private var _broadCastListLiveData = MutableLiveData<TalkState>(TalkState.UnInitialized)
    val broadCastListLiveData: LiveData<TalkState> = _broadCastListLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _broadCastListLiveData.postValue(TalkState.Loading)
    }

    fun getBroadCastList(cate: String){
        viewModelScope.launch {
            val response = getBroadCastListUseCase(cate)
            if (response == null) {
                _broadCastListLiveData.postValue(TalkState.Error)
            } else{
                _broadCastListLiveData.postValue(TalkState.Success(response))
            }
        }
    }
}