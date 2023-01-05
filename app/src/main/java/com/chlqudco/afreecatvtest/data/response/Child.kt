package com.chlqudco.afreecatvtest.data.response


import com.google.gson.annotations.SerializedName

data class Child(
    @SerializedName("cate_name")
    val cateName: String,
    @SerializedName("cate_no")
    val cateNo: String
)