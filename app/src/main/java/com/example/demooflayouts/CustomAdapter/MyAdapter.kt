package com.example.demooflayouts.CustomAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.demooflayouts.fragments.ImgFragment
import com.example.demooflayouts.ViewPager_TabLayout
import com.example.demooflayouts.fragments.SettingFragment
import com.example.demooflayouts.fragments.VideoFragment

class MyAdapter(
   val viewpagerTablayout: ViewPager_TabLayout,
   supportFragmentManager: FragmentManager,
   val tabCount: Int
) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> ImgFragment()
            1 -> VideoFragment()
            2 -> SettingFragment()
            else -> Fragment()
        }
    }
}