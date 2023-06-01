package com.example.demooflayouts.retrofitTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.R
import com.example.demooflayouts.retrofitTask.repository.ProductServices
import com.example.demooflayouts.retrofitTask.viewModel.ProductData
import com.example.demooflayouts.retrofitTask.viewModel.ProductsDetail
import com.example.demooflayouts.roomDBTask.MyTaskAdapter
import com.example.demooflayouts.roomDBTask.TaskDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductListActivity : AppCompatActivity(), ProductsAdapter.OnProductClick {

    lateinit var recyclerView : RecyclerView
    lateinit var productsAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        recyclerView = findViewById(R.id.recyclerProducts)



        val retrofit =   Retrofit.Builder().baseUrl(PRODUCT_LIST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductServices::class.java)


        getProductList(retrofit.listOfProduct())

    }

    private fun getProductList(listOfProduct: Call<ProductData>) {
        val call : Call<ProductData> = listOfProduct
        call.enqueue(object : Callback<ProductData>{
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                findViewById<ProgressBar>(R.id.progress_products).visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                showProduct(response.body()?.products)
//                println(response.body()?.products.toString())
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                println(t)
            }

        })
    }

    private fun showProduct(productsList: ArrayList<ProductsDetail>?) {
        if(productsList != null){
            productsAdapter = ProductsAdapter(this)
            productsAdapter.setProductData(productsList)
            recyclerView.adapter = productsAdapter
        }else{
            Toast.makeText(this, "No Product Available", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val PRODUCT_LIST_URL= "https://dummyjson.com/"
    }

    override fun onClick(position: Int, productList: ArrayList<ProductsDetail>) {

        val data = productList[position]
        val productsDetail = ProductsDetail(data.id,data.title,data.description,data.price,data.discountPercentage,data.rating,data.stock,data.brand,data.category,data.thumbnail,data.images)
        val intent = Intent(this,DetailsActivity::class.java)
        intent.putExtra("details",productsDetail)
        startActivity(intent)
    }

}