package com.chlqudco.afreecatvtest.presentation.broadlist

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chlqudco.afreecatvtest.data.response.Broad
import com.chlqudco.afreecatvtest.domain.GetBroadListByPagingUseCase
import com.chlqudco.afreecatvtest.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class BroadListViewModel(
    private val getBroadListByPagingUseCase: GetBroadListByPagingUseCase
) : BaseViewModel() {

    private val _broadListPagingResult = MutableStateFlow<PagingData<Broad>>(PagingData.empty())
    val broadListPagingResult: StateFlow<PagingData<Broad>> = _broadListPagingResult.asStateFlow()

    fun getBroadListByPaging(cate: String){
        viewModelScope.launch {
            getBroadListByPagingUseCase(cate)
                .cachedIn(viewModelScope)
                .collect{
                    _broadListPagingResult.value = it
                }
        }
    }

    override fun fetchData(): Job = viewModelScope.launch {}
}