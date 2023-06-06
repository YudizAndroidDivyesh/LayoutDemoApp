package com.example.demooflayouts.retrofitTask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.R
import com.example.demooflayouts.databinding.ProductsLayoutBinding
import com.example.demooflayouts.retrofitTask.viewModel.ProductsDetail
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private val onProductClick: OnProductClick
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    private var productList = emptyList<ProductsDetail>()

    private lateinit var binding: ProductsLayoutBinding
    class ProductViewHolder(
       var view : ProductsLayoutBinding,
        onProductClick: OnProductClick,
        productDataList: ArrayList<ProductsDetail>
    ) : RecyclerView.ViewHolder(view.root) {
//        fun bind(holder: ProductViewHolder, position: Int, productList: ArrayList<ProductsDetail>) {
//            val product = productList[position]
//            holder.productTitle.text = product.title
//            holder.productPrice.text = "$" + product.price.toString()
//            holder.productCategory.text = product.category
//            Picasso.get().load(product.thumbnail).placeholder(R.drawable.baseline_image_24).into(holder.productImg)
//        }
        init {
            itemView.setOnClickListener {
                onProductClick.onClick(adapterPosition, productDataList)
            }
        }
//        private val productImg = itemView.findViewById<ImageView>(R.id.thumbnail_iv)
//
//        private val productTitle = itemView.findViewById<TextView>(R.id.product_title_tv)
//        private val productPrice = itemView.findViewById<TextView>(R.id.price_tv)
//        private val productCategory = itemView.findViewById<TextView>(R.id.category_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view = layoutInflater.inflate(R.layout.products_layout, parent, false)
//        return ProductViewHolder(view, onProductClick, productList as ArrayList<ProductsDetail>)
        binding = ProductsLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return ProductViewHolder(binding,onProductClick,productList as ArrayList<ProductsDetail>)
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
       // holder.bind(holder, position, productList as ArrayList<ProductsDetail>)
         holder.view.productData = productList[position]
        holder.view.imgUrl = productList[position].thumbnail
    }

    fun setProductData(list: ArrayList<ProductsDetail>) {
        productList = list
    }

    interface OnProductClick {
        fun onClick(position: Int, productList: ArrayList<ProductsDetail>)
    }

}
@BindingAdapter("imgUrl")
fun ImageView.loadImg(url : String){
    Picasso.get().load(url).placeholder(R.drawable.baseline_image_24).into(this)
}