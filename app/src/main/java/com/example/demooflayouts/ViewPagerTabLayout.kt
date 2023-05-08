package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.demooflayouts.CustomAdapter.MyAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.GRAVITY_FILL

class ViewPagerTabLayout : AppCompatActivity() {

    var tabLayout: TabLayout? = null
    var viewPage: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_tab_layout)


        tabLayout = findViewById(R.id.tabs)
        viewPage = findViewById(R.id.viewpage)

        tabLayout?.addTab(
            tabLayout!!.newTab().setText("Images").setIcon(R.drawable.baseline_image_24)
        )
        tabLayout?.addTab(tabLayout!!.newTab().setText("Videos"))
        tabLayout?.addTab(tabLayout!!.newTab().setText("Settings"))
        tabLayout?.tabGravity = GRAVITY_FILL


        setAdapter()

        bindingView()

        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPage?.currentItem = tab!!.position

//                Toast.makeText(applicationContext, "${tab?.position}", Toast.LENGTH_SHORT).show()

                viewPage?.currentItem = tab.position


                when (tab.position) {
                    0 -> {
                        tabLayout?.getTabAt(0)?.setIcon(R.drawable.baseline_image_24)?.text =
                            "Image"
                    }
                    1 -> {
                        tabLayout?.getTabAt(1)?.setIcon(R.drawable.baseline_video_file_24)?.text =
                            "Video"

                    }
                    2 -> {
                        tabLayout?.getTabAt(2)?.setIcon(R.drawable.baseline_settings_24)?.text =
                            "Settings"
                    }

                }
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.icon = null
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })


    }

    private fun setAdapter() {
        val pageAdapter = MyAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPage!!.adapter = pageAdapter

    }

    private fun bindingView() {
        viewPage!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    }

}