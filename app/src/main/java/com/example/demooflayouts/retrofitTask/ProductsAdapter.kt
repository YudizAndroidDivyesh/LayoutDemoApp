package com.example.demooflayouts.retrofitTask

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.R
import com.example.demooflayouts.databinding.ProductsLayoutBinding
import com.example.demooflayouts.retrofitTask.viewModel.ProductsDetail
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private val productList: ArrayList<ProductsDetail>,
    private val clickListener: (ProductsDetail) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private lateinit var binding: ProductsLayoutBinding

    class ProductViewHolder(
        var view: ProductsLayoutBinding, clickAtPosition: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(view.root) {
        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        binding = ProductsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding) {
            clickListener(productList[it])
        }
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.view.productData = productList[position]
        holder.view.imgUrl = productList[position].thumbnail
    }
}

@BindingAdapter("imgUrl")
fun ImageView.loadImg(url: String) {
    Picasso.get().load(url).placeholder(R.drawable.baseline_image_24).into(this)
}