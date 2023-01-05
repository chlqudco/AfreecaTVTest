package com.chlqudco.afreecatvtest.data.response


import com.google.gson.annotations.SerializedName

data class BroadCategory(
    @SerializedName("cate_name")
    val cateName: String,
    @SerializedName("cate_no")
    val cateNo: String,
    @SerializedName("child")
    val child: List<Child>
)