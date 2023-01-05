package com.chlqudco.afreecatvtest.presentation.talk

import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlqudco.afreecatvtest.databinding.FragmentTalkBinding
import com.chlqudco.afreecatvtest.presentation.adapter.BroadCastAdapter
import com.chlqudco.afreecatvtest.presentation.base.BaseFragment
import com.chlqudco.afreecatvtest.presentation.main.MainActivity
import org.koin.android.ext.android.inject

internal class TalkFragment : BaseFragment<TalkViewModel, FragmentTalkBinding>() {

    private lateinit var adapter: BroadCastAdapter

    override val viewModel by inject<TalkViewModel>()

    override fun getViewBinding() = FragmentTalkBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.broadCastListLiveData.observe(this){
            when(it){
                is TalkState.UnInitialized -> initViews()
                is TalkState.Loading -> {}
                is TalkState.Success -> handleSuccessState(it)
                is TalkState.Error -> handleErrorState()
            }
        }
    }

    private fun initViews() {
        adapter = BroadCastAdapter( broadClickListener = {
            // 이동
            (activity as MainActivity).moveDetailActivity(it)
        })
        binding.FragmentTalkRecyclerView.adapter = adapter
        binding.FragmentTalkRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun getBroadCastListByNumber(num: String){
        viewModel.getBroadCastList(num)
    }

    private fun handleSuccessState(state: TalkState.Success) {
        adapter.broadList = state.list.broad
        adapter.notifyDataSetChanged()
    }

    private fun handleErrorState() {
        Toast.makeText(context, "에러 발생", Toast.LENGTH_SHORT).show()
    }
}