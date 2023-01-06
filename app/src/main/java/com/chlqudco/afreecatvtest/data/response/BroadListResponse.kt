package com.chlqudco.afreecatvtest.data.response


import com.google.gson.annotations.SerializedName

data class BroadListResponse(
    @SerializedName("broad")
    val broad: List<Broad>,
    @SerializedName("page_block")
    val pageBlock: Int,
    @SerializedName("page_no")
    val pageNo: Int,
    @SerializedName("time")
    val time: Int,
    @SerializedName("total_cnt")
    val totalCnt: Int
)