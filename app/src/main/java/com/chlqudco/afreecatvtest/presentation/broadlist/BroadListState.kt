package com.chlqudco.afreecatvtest.presentation.broadlist

import com.chlqudco.afreecatvtest.data.response.BroadListResponse

internal sealed class BroadListState {

    object UnInitialized: BroadListState()

    object Loading: BroadListState()

    data class Success(
        val list: BroadListResponse
    ): BroadListState()

    object Error: BroadListState()
}