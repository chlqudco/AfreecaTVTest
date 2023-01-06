package com.chlqudco.afreecatvtest.presentation.main

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.chlqudco.afreecatvtest.data.response.Broad
import com.chlqudco.afreecatvtest.databinding.ActivityMainBinding
import com.chlqudco.afreecatvtest.presentation.adapter.FragmentAdapter
import com.chlqudco.afreecatvtest.presentation.base.BaseActivity
import com.chlqudco.afreecatvtest.presentation.detail.DetailActivity
import com.chlqudco.afreecatvtest.presentation.broadlist.BroadListFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

internal class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val categoryMap: MutableMap<String, String> = mutableMapOf()
    private val tabTitles: MutableList<String> = mutableListOf()
    private val fragmentList: MutableList<Fragment> = mutableListOf()

    override val viewModel by inject<MainViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.categoryListLiveData.observe(this) {
            when (it) {
                is MainState.UnInitialized -> initViews()
                is MainState.Loading -> getCategoryList()
                is MainState.Success -> handleSuccessState(it)
                is MainState.Error -> handleErrorState()
            }
        }
    }

    private fun getCategoryList() {
        viewModel.getCategoryList()
    }

    private fun handleSuccessState(state: MainState.Success) {

        val adapter = FragmentAdapter(this)
        adapter.fragmentList = fragmentList
        binding.MainActivityViewPager.adapter = adapter

        for (cate in state.list.broadCategory) {
            categoryMap[cate.cateName] = cate.cateNo
            tabTitles.add(cate.cateName)
            fragmentList.add(BroadListFragment())
        }

        TabLayoutMediator(binding.MainActivityTabLayout, binding.MainActivityViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        (fragmentList[0] as BroadListFragment).getBroadCastListByNumber(categoryMap["토크/캠방"]!!)

        binding.MainActivityTabLayout.addOnTabSelectedListener(object  : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                (fragmentList[tab?.position!!] as BroadListFragment).getBroadCastListByNumber(categoryMap[tab.text]!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }

    private fun handleErrorState() {
        Toast.makeText(this, "에러 발생", Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        setSupportActionBar(binding.MainActivityToolbar)
    }

    fun moveDetailActivity(broad: Broad){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("broad",broad)
        startActivity(intent)
    }
}