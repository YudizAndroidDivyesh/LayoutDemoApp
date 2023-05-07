package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.demooflayouts.CustomAdapter.MyUserAdapter
import com.example.demooflayouts.CustomAdapter.User

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    val userdatalist = ArrayList<User>()
    lateinit var deleteAll: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        recyclerView = findViewById(R.id.rvView)
        deleteAll = findViewById(R.id.delete_records_btn)



        addDataToList()

        val myadapter = MyUserAdapter(this, userdatalist)
        recyclerView.adapter = myadapter
        deleteAll.setOnClickListener(object : OnClickListener {
            override fun onClick(p0: View?) {
                Toast.makeText(this@RecyclerViewActivity, "clcick", Toast.LENGTH_SHORT).show()

            val l = userdatalist.filter { it.isCheck }
                //userdatalist.removeAll(l)
                var itemIndex  = 0
//                myadapter.notifyItemRangeChanged(0,userdatalist.filter { it.isCheck }.size)
                for (i in l){
                    itemIndex  = userdatalist.indexOf(i)
                    userdatalist.removeAt(itemIndex)
                    myadapter.notifyItemRemoved(itemIndex)
                    myadapter.notifyItemRangeChanged(itemIndex,userdatalist.size)

                }
            }
        })
    }

    private fun addDataToList() {
        userdatalist.add(User("A", 123467890, "d.v@mail.com",false))
        userdatalist.add(User("B", 7894512369, "k.n@mail.com",false))
        userdatalist.add(User("C", 1245789630, "p.p@mail.com",false))
        userdatalist.add(User("D", 7845120369, "a.p@mail.com",false))
        userdatalist.add(User("E", 123145893, "a.b@mail.com",false))
        userdatalist.add(User("F", 123467890, "d.v@mail.com",false))
        userdatalist.add(User("G", 7894512369, "k.n@mail.com",false))
        userdatalist.add(User("H", 1245789630, "p.p@mail.com",false))
        userdatalist.add(User("I", 7845120369, "a.p@mail.com",false))
        userdatalist.add(User("J", 123145893, "a.b@mail.com",false))
        userdatalist.add(User("K", 123467890, "d.v@mail.com",false))
        userdatalist.add(User("L", 7894512369, "k.n@mail.com",false))
        userdatalist.add(User("M", 1245789630, "p.p@mail.com",false))
        userdatalist.add(User("N", 7845120369, "a.p@mail.com",false))
        userdatalist.add(User("P", 123145893, "a.b@mail.com",false))
    }
}