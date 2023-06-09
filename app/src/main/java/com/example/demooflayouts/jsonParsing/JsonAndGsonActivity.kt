package com.example.demooflayouts.jsonParsing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.demooflayouts.R
import com.example.demooflayouts.retrofitTask.ProductListActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONArray
import org.json.JSONObject

class JsonAndGsonActivity : AppCompatActivity() {

    val jsonString = "{\"id\":1,\"title\":\"His mother had always taught him\",\"body\":\"He never looked down on those who were less fortunate or who had less money than him. But the stupidity of the group of people he was talking to made him change his mind.\",\"userId\":9,\"tags\":[\"history\",\"american\",\"crime\"],\"reactions\":2,\"geo\": {\n" +
            "      \"lat\": \"-37.3159\",\n" +
            "      \"lng\": \"81.1496\"\n" +
            "    }  }"

 //  val jsonString =  "{\"products\":[{\"id\":1,\"title\":\"iPhone 9\",\"description\":\"An apple mobile which is nothing like apple\",\"price\":549,\"discountPercentage\":12.96,\"rating\":4.69,\"stock\":94,\"brand\":\"Apple\",\"category\":\"smartphones\",\"thumbnail\":\"https://i.dummyjson.com/data/products/1/thumbnail.jpg\",\"images\":[\"https://i.dummyjson.com/data/products/1/1.jpg\",\"https://i.dummyjson.com/data/products/1/2.jpg\",\"https://i.dummyjson.com/data/products/1/3.jpg\",\"https://i.dummyjson.com/data/products/1/4.jpg\",\"https://i.dummyjson.com/data/products/1/thumbnail.jpg\"]}],\"total\":100,\"skip\":0,\"limit\":30}"

    val jsonObject = JSONObject(jsonString)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_and_gson)
        findViewById<Button>(R.id.btn_json_data).setOnClickListener {
            coreJsonData()
        }
        findViewById<Button>(R.id.btn_gson).setOnClickListener {
            gsonData()
        }
        findViewById<Button>(R.id.retrofit_btn).setOnClickListener {
            val intent = Intent(this,ProductListActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.retrofit_btn).setOnClickListener {
            val intent = Intent(this,ProductListActivity::class.java)
            startActivity(intent)
        }

    }

    private fun gsonData() {
        val gson = Gson()

        //Serialized jsonString to Data
        val userPost = gson.fromJson(jsonString,UserPost::class.java)
        println("Serialized jsonString to Data : $userPost")

        //DeSerialized Data to JsonString
        val deSerial = gson.toJson(userPost)
        println("DeSerialized Data to JsonString : $deSerial")

        val gsonBuilder = GsonBuilder().
            excludeFieldsWithoutExposeAnnotation().
            create()
        //Expose Serialized jsonString to Data
        val buildUserPost = gsonBuilder.fromJson(jsonString,UserPost::class.java)
        println("Expose Serialized jsonString to Data : $buildUserPost")

        val deBuildUserPost = gsonBuilder.toJson(buildUserPost)
        println("Expose Serialized jsonString to Data : $deBuildUserPost")

    }

    private fun coreJsonData() { //.getJSONObject(0).getString("title")
        val id = jsonObject.getInt("id")
        val title = jsonObject.getString("title")
        val body = jsonObject.getString("body")
        val userId = jsonObject.getInt("userId")
        val reactions = jsonObject.getInt("reactions")
        val geoLat = jsonObject.getJSONObject("geo").getDouble("lat")
        val geoLng = jsonObject.getJSONObject("geo").getDouble("lng")

        val post  = Post(id,title,body,userId,reactions,Geo(geoLat,geoLng))
        println(post.toString())
    }
}