package com.example.demooflayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.demooflayouts.fragments.ImgFragment
import com.example.demooflayouts.fragments.SettingFragment
import com.example.demooflayouts.fragments.VideoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentAndNavigationBar : AppCompatActivity(),FragmentCommunicater {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_and_navigation_bar)

        loadFragment(ImgFragment())


        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    loadFragment(ImgFragment())
                    true
                }
                R.id.page_2 -> {
                    loadFragment(VideoFragment())
                    true
                }
                R.id.page_3 -> {
                    loadFragment(SettingFragment())
                    true
                }
               else -> {
                   loadFragment(Fragment())
                   true
               }
            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.frameLayout,fragment)
            .commit()
    }

    override fun passData(msg: String) {
        Toast.makeText(this, "$msg", Toast.LENGTH_SHORT).show()
    }
}