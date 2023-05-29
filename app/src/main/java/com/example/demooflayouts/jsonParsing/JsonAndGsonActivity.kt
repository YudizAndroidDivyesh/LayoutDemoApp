package com.example.demooflayouts.jsonParsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.demooflayouts.R
import org.json.JSONArray
import org.json.JSONObject

class JsonAndGsonActivity : AppCompatActivity() {


    val jsonString = "{\"id\":1,\"title\":\"His mother had always taught him\",\"body\":\"He never looked down on those who were less fortunate or who had less money than him. But the stupidity of the group of people he was talking to made him change his mind.\",\"userId\":9,\"tags\":[\"history\",\"american\",\"crime\"],\"reactions\":2,\"geo\": {\n" +
            "      \"lat\": \"-37.3159\",\n" +
            "      \"lng\": \"81.1496\"\n" +
            "    }  }"

    val jsonObject = JSONObject(jsonString)
    val jsonArray = JSONArray()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_and_gson)
        findViewById<Button>(R.id.btn_json_data).setOnClickListener {
            coreJsonData()
        }
    }

    private fun coreJsonData() {

        println(jsonObject.toString())

        val id = jsonObject.getInt("id")
        val title = jsonObject.getString("title")
        val body = jsonObject.getString("body")
        val userId = jsonObject.getInt("userId")
        val tags = jsonObject.getJSONArray("tags")
        val reactions = jsonObject.getInt("reactions")
        val geoLat = jsonObject.getJSONObject("geo").getDouble("lat")
        val geoLng = jsonObject.getJSONObject("geo").getDouble("lng")

        val post  = Post(id,title,body,userId,tags,reactions,Geo(geoLat,geoLng))
        println(post.toString())
    }
}