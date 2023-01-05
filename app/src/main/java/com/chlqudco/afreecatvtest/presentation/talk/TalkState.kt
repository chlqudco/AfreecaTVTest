package com.chlqudco.afreecatvtest.presentation.talk

import com.chlqudco.afreecatvtest.data.response.BroadCastListResponse

internal sealed class TalkState {

    object UnInitialized: TalkState()

    object Loading: TalkState()

    data class Success(
        val list: BroadCastListResponse
    ): TalkState()

    object Error: TalkState()
}