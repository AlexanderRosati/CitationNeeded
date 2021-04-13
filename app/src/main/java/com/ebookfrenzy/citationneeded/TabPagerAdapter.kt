package com.ebookfrenzy.citationneeded

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabPagerAdapter(fm : FragmentManager, private var app : Application, private var tabCount : Int) :
    FragmentPagerAdapter(fm,
        FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position : Int) : Fragment {
        when (position) {
            0 -> return NewCitationFragment(app)
            1 -> return SearchByTagFragment()
            2 -> return SearchByAuthorFragment()
            3 -> return SearchByBookFragment()
            else -> return NewCitationFragment(app)
        }
    }

    override fun getCount() : Int {
        return tabCount
    }

}