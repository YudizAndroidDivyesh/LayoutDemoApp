package com.example.demooflayouts

import android.content.BroadcastReceiver
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.demooflayouts.broadCastReceiver.BroadCastPercentageActivity
import com.example.demooflayouts.workManager.DownloadFileActivity

class AllTaskList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_task_list)

        val task1 = findViewById<Button>(R.id.layout1)
        val task2 = findViewById<Button>(R.id.view1)
        val task3 = findViewById<Button>(R.id.btn_custom)
        val task4 = findViewById<Button>(R.id.Drawables1)
        val task5 = findViewById<Button>(R.id.VSD)
        val task6 = findViewById<Button>(R.id.tabLayoutViewPage)
        val task7 = findViewById<Button>(R.id.rvButton)
        val task8 = findViewById<Button>(R.id.persmissionBtn)
        val task9 = findViewById<Button>(R.id.fragmentBtn)
        val task10 = findViewById<Button>(R.id.alertDialog)
        val task11 = findViewById<Button>(R.id.noifye)
        val task12 = findViewById<Button>(R.id.intentAction)
        val task13 = findViewById<Button>(R.id.broadCast)
        val task14 = findViewById<Button>(R.id.workManager)
        val task15 = findViewById<Button>(R.id.save_file_btn)
        val task16 = findViewById<Button>(R.id.btn_animation)


        task1.setOnClickListener {
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }
        task2.setOnClickListener { startActivity(Intent(applicationContext,AllViewActivity::class.java))
        }
        task3.setOnClickListener {
            startActivity(Intent(applicationContext,CustomComponents::class.java))
        }
        task4.setOnClickListener {
            startActivity(Intent(applicationContext,MetarialUI::class.java))
        }
        task5.setOnClickListener {
            startActivity(Intent(applicationContext,VectorShapesAndDrawableSelector::class.java))
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
        task11.setOnClickListener {
            startActivity(Intent(applicationContext,AllNotification::class.java))
        }
        task12.setOnClickListener {
            startActivity(Intent(applicationContext,IntentFilterActionActivity::class.java))
        }
        task13.setOnClickListener {
            startActivity(Intent(applicationContext,BroadCastPercentageActivity::class.java))
        }
        task14.setOnClickListener {
            startActivity(Intent(applicationContext,DownloadFileActivity::class.java))
        }
        task15.setOnClickListener {
            startActivity(Intent(applicationContext,SaveFileActivity::class.java))
        }
        task16.setOnClickListener {
            startActivity(Intent(applicationContext,ViewAnimationActivity::class.java))
        }
    }
}