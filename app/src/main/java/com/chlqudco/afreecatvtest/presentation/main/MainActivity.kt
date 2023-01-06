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

    //카테고리 종류를 Map으로 관리
    private val categoryMap: MutableMap<String, String> = mutableMapOf()

    //탭 레이아웃과 뷰페이저를 위한 리스트
    private val tabTitles: MutableList<String> = mutableListOf()
    private val fragmentList: MutableList<Fragment> = mutableListOf()

    override val viewModel by inject<MainViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun observeData() {
        //카테고리 리스트를 State 패턴으로 관리
        viewModel.categoryListLiveData.observe(this) {
            when (it) {
                is MainState.UnInitialized -> initViews()
                is MainState.Loading -> getCategoryList()
                is MainState.Success -> handleSuccessState(it)
                is MainState.Error -> handleErrorState()
            }
        }
    }

    //메인 액티비티 생성시 카테고리 리스트 가져와야 함
    private fun getCategoryList() {
        viewModel.getCategoryList()
    }

    private fun handleSuccessState(state: MainState.Success) {

        //어댑터 연결
        val adapter = FragmentAdapter(this)
        adapter.fragmentList = fragmentList
        binding.MainActivityViewPager.adapter = adapter

        //불러온 카테고리 정보를 적절히 저장해두기
        for (cate in state.list.broadCategory) {
            categoryMap[cate.cateName] = cate.cateNo
            tabTitles.add(cate.cateName)
            fragmentList.add(BroadListFragment())
        }

        //탭 레이아웃과 뷰페이저 연결
        TabLayoutMediator(binding.MainActivityTabLayout, binding.MainActivityViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        //앱 첫 실행시 첫 카테고리에 대한 방송 리스트 로딩
        (fragmentList[0] as BroadListFragment).getBroadCastListByNumber(categoryMap[tabTitles[0]]!!)

        //탭 레이아웃 선택시 선택한 카테고리에 대한 방송 불러오기
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
        //선택한 Broad 정보를 인텐트에 담아 넘기기
        intent.putExtra("broad",broad)
        startActivity(intent)
    }
}