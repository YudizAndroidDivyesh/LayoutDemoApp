package com.example.demooflayouts.liveDataViewModel.viewModelFile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.R
import com.example.demooflayouts.databinding.NewsRvDataBinding
import com.example.demooflayouts.liveDataViewModel.data.Articles
import com.squareup.picasso.Picasso

class NewsAdapter(private var newsDataList : List<Articles>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private lateinit var binding : NewsRvDataBinding
    class NewsViewHolder(var view: NewsRvDataBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       binding = NewsRvDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount() = newsDataList.size


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.view.newsData = newsDataList[position]
        holder.view.newsImg = newsDataList[position].urlToImage

    }
}

@BindingAdapter("newsImgUrl")
fun ImageView.loadNewsImg(url : String){
    Picasso.get().load(url).placeholder(R.drawable.baseline_image_24).into(this)
}