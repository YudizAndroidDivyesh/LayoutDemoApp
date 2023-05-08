package com.example.demooflayouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class allTaskList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_task_list)

        var task1 = findViewById<Button>(R.id.layout1)
        var task2 = findViewById<Button>(R.id.view1)
        var task3 = findViewById<Button>(R.id.btn_custom)
        var task4 = findViewById<Button>(R.id.Drawables1)
        var task5 = findViewById<Button>(R.id.VSD)
        var task6 = findViewById<Button>(R.id.tabLayoutViewPage)
        var task7 = findViewById<Button>(R.id.rvButton)
        var task8 = findViewById<Button>(R.id.persmissionBtn)
        var task9 = findViewById<Button>(R.id.fragmentBtn)
        var task10 = findViewById<Button>(R.id.alertDialog)


        task1.setOnClickListener {
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }
        task2.setOnClickListener { startActivity(Intent(applicationContext,allViewActivity::class.java))
        }
        task3.setOnClickListener {
            startActivity(Intent(applicationContext,CustomComponents::class.java))
        }
        task4.setOnClickListener {
            startActivity(Intent(applicationContext,MetarialUI::class.java))
        }
        task5.setOnClickListener {
            startActivity(Intent(applicationContext,Vector_Shapes_and_Drawable_Selector::class.java))
        }
        task6.setOnClickListener {
            startActivity(Intent(applicationContext,ViewPagerTabLayout::class.java))
        }
        task7.setOnClickListener {
            startActivity(Intent(applicationContext,RecyclerViewActivity::class.java))
        }
        task8.setOnClickListener {
            startActivity(Intent(applicationContext,RuntimePermissionStartActivity::class.java))
        }
        task9.setOnClickListener {
            startActivity(Intent(applicationContext,FragmentAndNavigationBar::class.java))
        }
        task10.setOnClickListener {
            startActivity(Intent(applicationContext,AlertDialogWithTimeDatePicker::class.java))
        }
    }
}