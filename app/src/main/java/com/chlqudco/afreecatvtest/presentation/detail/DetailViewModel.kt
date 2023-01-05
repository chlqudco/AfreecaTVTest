package com.chlqudco.afreecatvtest.presentation.detail

import androidx.lifecycle.viewModelScope
import com.chlqudco.afreecatvtest.presentation.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class DetailViewModel: BaseViewModel() {
    override fun fetchData(): Job = viewModelScope.launch{ }
}