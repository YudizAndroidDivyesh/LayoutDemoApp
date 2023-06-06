package com.example.demooflayouts.retrofitTask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.R
import com.example.demooflayouts.databinding.ActivityProductListBinding
import com.example.demooflayouts.databinding.ProductsLayoutBinding
import com.example.demooflayouts.retrofitTask.repository.ProductServices
import com.example.demooflayouts.retrofitTask.viewModel.ProductData
import com.example.demooflayouts.retrofitTask.viewModel.ProductsDetail
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductListActivity : AppCompatActivity(){

    lateinit var recyclerView: RecyclerView
    private lateinit var productsAdapter: ProductsAdapter
    lateinit var binding: ActivityProductListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
                .baseUrl(PRODUCT_LIST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val productServices = retrofit.create(ProductServices::class.java)


        fetchProducts(productServices.listOfProduct())
    }

    private fun fetchProducts(listOfProduct: Call<ProductData>) {
        val call: Call<ProductData> = listOfProduct
        call.enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                binding.progressProducts.visibility = View.GONE
                binding.recyclerProducts.visibility = View.VISIBLE


                showProducts(response.body()?.products)
//                println(response.body()?.products.toString())
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                println(t)
            }

        })
    }

    private fun showProducts(productsList: ArrayList<ProductsDetail>?) {
        if (productsList != null) {
            productsAdapter = ProductsAdapter(productsList){
                println(it.id)
                val intent = Intent(this, DetailsActivity::class.java)
                intent.putExtra("productId", it.id)
                startActivity(intent)
            }
            binding.recyclerProducts.adapter = productsAdapter
        } else {
            Toast.makeText(this, R.string.no_products, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val PRODUCT_LIST_URL = "https://dummyjson.com/"
    }

}