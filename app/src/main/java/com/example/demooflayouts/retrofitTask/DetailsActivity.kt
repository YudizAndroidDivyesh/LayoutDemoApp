package com.example.demooflayouts.retrofitTask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.example.demooflayouts.R
import com.example.demooflayouts.databinding.ActivityDetailsBinding
import com.example.demooflayouts.retrofitTask.repository.ProductServices
import com.example.demooflayouts.retrofitTask.viewModel.ProductsDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ImgPagerAdapter

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = findViewById(R.id.products_iv)

        val productId = intent.getIntExtra("productId", 0)

        val retroFit = Retrofit.Builder().baseUrl(ProductListActivity.PRODUCT_LIST_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val productsDetailServices = retroFit.create(ProductServices::class.java)

        fetchDataFromUrl(productsDetailServices.getOneProduct(productId))
    }

    private fun fetchDataFromUrl(oneProductsDetail: Call<ProductsDetail>) {
        val call: Call<ProductsDetail> = oneProductsDetail
        call.enqueue(object : Callback<ProductsDetail> {
            override fun onResponse(
                call: Call<ProductsDetail>,
                response: Response<ProductsDetail>
            ) {
                println("Connection...")
                binding.detailsProgress.visibility = View.GONE
                showProductDetails(response.body())
            }

            override fun onFailure(call: Call<ProductsDetail>, t: Throwable) {
                println("Failed....")
            }

        })

    }

    private fun showProductDetails(body: ProductsDetail?) {
//                if (intent.extras != null) {
//                    productData = intent.getSerializableExtra("details") as ProductsDetail
//                }

        //    Without viewBinding to find textView
        //  findViewById<TextView>(R.id.product_detail_title_tv).text = productData.title

        //Using viewBinding to find TextView
        binding.productDetailTitleTv.text = body?.title
        binding.descriptionTv.text = getString(R.string.description, body?.description)
        binding.priceTv.text = getString(R.string.dollar_sign, body?.price)
        binding.discountPercentageTv.text =
            getString(R.string.discount, body?.discountPercentage.toString())
        binding.stockTv.text = getString(R.string.stock, body?.stock)
        binding.brandTv.text = getString(R.string.brandName, body?.brand)

        viewPagerAdapter = ImgPagerAdapter(this, body!!.images)
        viewPager.adapter = viewPagerAdapter

        binding.indicator.setupWithViewPager(viewPager, true)
    }
}

