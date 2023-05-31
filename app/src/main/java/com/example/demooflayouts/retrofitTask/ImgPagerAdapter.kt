package com.example.demooflayouts.retrofitTask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.demooflayouts.R
import com.squareup.picasso.Picasso
import java.util.Objects

class ImgPagerAdapter(val context : Context, private val imgList : ArrayList<String>) :
    PagerAdapter() {
    override fun getCount(): Int {
        return imgList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mLayout = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = mLayout.inflate(R.layout.slider_image,container,false)
        val imgView = view.findViewById<ImageView>(R.id.iv_slider)
        Picasso.get().load(imgList[position]).into(imgView)
        Objects.requireNonNull(container).addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }


}