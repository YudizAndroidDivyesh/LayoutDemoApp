package com.example.demooflayouts.retrofitTask.viewModel

import com.google.gson.annotations.SerializedName

data class ProductData(

    @SerializedName("products" ) var products : ArrayList<ProductsDetail> = arrayListOf(),
    @SerializedName("total"    ) var total    : Int?                = null,
    @SerializedName("skip"     ) var skip     : Int?                = null,
    @SerializedName("limit"    ) var limit    : Int?                = null

)