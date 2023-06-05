package com.example.demooflayouts.retrofitTask.repository

import com.example.demooflayouts.retrofitTask.viewModel.ProductData
import com.example.demooflayouts.retrofitTask.viewModel.ProductsDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductServices {

      @GET("products")
      fun listOfProduct() : Call<ProductData>

      @GET("products/{id}")
      fun getOneProduct(@Path("id") id : Int) : Call<ProductsDetail>

}