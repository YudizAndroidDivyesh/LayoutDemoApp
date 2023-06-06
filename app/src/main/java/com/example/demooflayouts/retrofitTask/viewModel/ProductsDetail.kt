package com.example.demooflayouts.retrofitTask.viewModel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.demooflayouts.R
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso
import java.io.Serializable

data class ProductsDetail(

    @SerializedName("id"                 ) var id                 : Int?              = null,
    @SerializedName("title"              ) var title              : String?           = null,
    @SerializedName("description"        ) var description        : String?           = null,
    @SerializedName("price"              ) var price              : Int?              = null,
    @SerializedName("discountPercentage" ) var discountPercentage : Double?           = null,
    @SerializedName("rating"             ) var rating             : Double?           = null,
    @SerializedName("stock"              ) var stock              : Int?              = null,
    @SerializedName("brand"              ) var brand              : String?           = null,
    @SerializedName("category"           ) var category           : String?           = null,
    @SerializedName("thumbnail"          ) var thumbnail          : String?           = null,
    @SerializedName("images"             ) var images             : ArrayList<String> = arrayListOf()
) : Serializable {

}

