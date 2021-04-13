package com.ebookfrenzy.citationneeded

import android.net.Uri
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.ebookfrenzy.citationneeded.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(),
        NewCitationFragment.OnFragmentInteractionListener,
        SearchByTagFragment.OnFragmentInteractionListener,
        SearchByAuthorFragment.OnFragmentInteractionListener,
        SearchByBookFragment.OnFragmentInteractionListener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //get binding
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        configureTabLayout()
    }

    private fun configureTabLayout() {
        //add tabs
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("New Citation"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Search by Tag"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Search by Author"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Search by Book"))

        //instantiate tab adapter and assign it to pager
        val adapter = TabPagerAdapter(supportFragmentManager, application, binding.tabLayout.tabCount)
        binding.pager.adapter = adapter

        //setting up pager
        binding.pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab : TabLayout.Tab) {
                binding.pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab : TabLayout.Tab) {

            }

            override fun onTabReselected(tab : TabLayout.Tab) {

            }
        })
    }

    override fun onFragmentInteraction(uri : Uri) {

    }
}