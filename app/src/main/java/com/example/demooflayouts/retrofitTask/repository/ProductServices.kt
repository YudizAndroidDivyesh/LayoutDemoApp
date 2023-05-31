package com.example.demooflayouts.retrofitTask.repository

import com.example.demooflayouts.retrofitTask.viewModel.ProductData
import retrofit2.Call
import retrofit2.http.GET

interface ProductServices {

      @GET("products")
      fun listOfProduct() : Call<ProductData>

}