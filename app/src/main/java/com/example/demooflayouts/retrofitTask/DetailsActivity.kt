package com.example.demooflayouts.retrofitTask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.demooflayouts.R
import com.example.demooflayouts.retrofitTask.viewModel.ProductsDetail
import com.google.android.material.tabs.TabLayout

class DetailsActivity : AppCompatActivity() {

    lateinit var productData : ProductsDetail
lateinit var viewPager : ViewPager
lateinit var viewPagerAdapter : ImgPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        viewPager = findViewById(R.id.products_iv)

        if(intent.extras != null){
            productData  = intent.getSerializableExtra("details") as ProductsDetail
        }

        findViewById<TextView>(R.id.product_detail_title_tv).text = productData.title
        findViewById<TextView>(R.id.description_tv).text = "Description : \n "+productData.description
        findViewById<TextView>(R.id.price_tv).text = "Price : $"+productData.price.toString()
        findViewById<TextView>(R.id.discountPercentage_tv_tv).text = "Discount "+productData.discountPercentage.toString()+"%"
        findViewById<TextView>(R.id.stock_tv).text = "Stock : "+productData.stock.toString()
        findViewById<TextView>(R.id.brand_tv).text = "Company Name "+productData.brand

        viewPagerAdapter = ImgPagerAdapter(this,productData.images)
        viewPager.adapter = viewPagerAdapter

        val a = findViewById<TabLayout>(R.id.indicator)
        a.setupWithViewPager(viewPager,true)

    }
}