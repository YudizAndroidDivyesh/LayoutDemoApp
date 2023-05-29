package com.example.demooflayouts.jsonParsing

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserPost(
    @Expose
    @SerializedName("id", alternate = ["Uid","pId"])
    var postId: Int,

    @Expose
    @SerializedName("title")
    var postTitle : String,

    @Expose
    @SerializedName("body")
    var postBody :  String,

    @Expose
    @SerializedName("userId")
    var postUserId: Int,

    @Expose
    @SerializedName("tags")
    var postTags : List<String>,

    @Expose
    @SerializedName("reactions")
    var postReactions: Int,

    @Expose(serialize = true , deserialize = false)
    @SerializedName("geo")
    var postGeo : PostGeo
) {
    override fun toString(): String {
        return "UserPost(postId=$postId, postTitle='$postTitle', postBody='$postBody', postUserId=$postUserId, postTags=$postTags, postReactions=$postReactions, postGeo=$postGeo)"
    }
}

data class PostGeo(val lat : Double ,val lng : Double){

}