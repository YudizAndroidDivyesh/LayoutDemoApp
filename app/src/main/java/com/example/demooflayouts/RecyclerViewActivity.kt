package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.CustomAdapter.MyUserAdapter
import com.example.demooflayouts.CustomAdapter.User

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    val userdatalist = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        recyclerView = findViewById(R.id.rvView)

        addDataToList()

        val myadapter = MyUserAdapter(this,userdatalist)

        recyclerView.adapter = myadapter

    }

    private fun addDataToList() {
        userdatalist.add(User("Divyesh",123467890,"d.v@mail.com"))
        userdatalist.add(User("Heet",7894512369,"k.n@mail.com"))
        userdatalist.add(User("Prakash",1245789630,"p.p@mail.com"))
        userdatalist.add(User("Anand",7845120369,"a.p@mail.com"))
        userdatalist.add(User("abhishek",123145893,"a.b@mail.com"))
    }
}