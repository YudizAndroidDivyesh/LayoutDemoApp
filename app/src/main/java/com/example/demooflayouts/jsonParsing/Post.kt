package com.example.demooflayouts.jsonParsing

import org.json.JSONArray

data class Post(
    var id: Int,
    var title: String,
    var body: String,
    var userId: Int,
    var tags: JSONArray,
    var reactions: Int,
    var geo : Geo

) {
    override fun toString(): String {
        return "Post(id=$id, title='$title', body='$body', userId=$userId, tags=$tags, reactions=$reactions, geo=$geo)"
    }
}

data class Geo(val lat : Double ,val lng : Double){

}