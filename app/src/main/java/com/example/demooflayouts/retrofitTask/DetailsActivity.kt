package com.example.demooflayouts.retrofitTask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.demooflayouts.R
import com.example.demooflayouts.retrofitTask.viewModel.ProductsDetail

class DetailsActivity : AppCompatActivity() {

    lateinit var productData : ProductsDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if(intent.extras != null){
            productData  = intent.getSerializableExtra("details") as ProductsDetail
        }

        findViewById<TextView>(R.id.product_detail_title_tv).text = productData.title
        findViewById<TextView>(R.id.description_tv).text = "Description : \n "+productData.description
        findViewById<TextView>(R.id.price_tv).text = "Price : $"+productData.price.toString()
        findViewById<TextView>(R.id.discountPercentage_tv_tv).text = "Discount "+productData.discountPercentage.toString()+"%"
        findViewById<TextView>(R.id.stock_tv).text = "Stock : "+productData.stock.toString()
        findViewById<TextView>(R.id.brand_tv).text = "Company Name "+productData.brand


    }
}