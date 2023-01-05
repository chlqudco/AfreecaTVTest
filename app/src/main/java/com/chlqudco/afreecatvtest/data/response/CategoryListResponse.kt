package com.chlqudco.afreecatvtest.data.response


import com.google.gson.annotations.SerializedName

data class CategoryListResponse(
    @SerializedName("broad_category")
    val broadCategory: List<BroadCategory>
)