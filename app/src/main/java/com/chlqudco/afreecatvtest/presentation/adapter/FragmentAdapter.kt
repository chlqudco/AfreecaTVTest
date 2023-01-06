package com.chlqudco.afreecatvtest.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


//뷰 페이저에 프래그먼트 연결
class FragmentAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    var fragmentList = listOf<Fragment>()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}