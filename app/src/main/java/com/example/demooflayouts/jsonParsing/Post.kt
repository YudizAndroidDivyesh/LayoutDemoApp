package com.example.demooflayouts.jsonParsing

import org.json.JSONArray

//data class Post(
//    var id: Int,
//    var title: String,
//    var body: String,
//    var userId: Int,
//    var reactions: Int,
//    var geo : Geo
//
//) {
//    override fun toString(): String {
//        return "Post(id=$id, title='$title', body='$body', userId=$userId,reactions=$reactions, geo=$geo)"
//    }
//}
//
//data class Geo(val lat : Double ,val lng : Double){
//
//}
//
//data class Tags(val tagName : ArrayList<Tags>)

data class Post(
     var products : ArrayList<PostProducts> = arrayListOf(),
    var total    : Int?                = null,
   var skip     : Int?                = null,
    var limit    : Int?                = null
){

}

data class PostProducts(
    var id                 : Int,
     var title              : String? = null,
    var description        : String? = null,
     var price              : Int,
    var discountPercentage : Double,
    var rating             : Double,
    var stock              : Int,
    var brand              : String? = null,
   var category           : String? = null,
     var thumbnail          : String? = null
)