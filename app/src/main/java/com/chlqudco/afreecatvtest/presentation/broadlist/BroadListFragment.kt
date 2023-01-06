package com.chlqudco.afreecatvtest.presentation.broadlist

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlqudco.afreecatvtest.databinding.FragmentBroadListBinding
import com.chlqudco.afreecatvtest.presentation.adapter.BroadListAdapter
import com.chlqudco.afreecatvtest.presentation.base.BaseFragment
import com.chlqudco.afreecatvtest.presentation.main.MainActivity
import org.koin.android.ext.android.inject

internal class BroadListFragment : BaseFragment<BroadListViewModel, FragmentBroadListBinding>() {

    private val adapter : BroadListAdapter by lazy {
        BroadListAdapter( broadClickListener = {
            // 이동
            (activity as MainActivity).moveDetailActivity(it)
        })
    }

    override val viewModel by inject<BroadListViewModel>()

    override fun getViewBinding() = FragmentBroadListBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.broadCastListLiveData.observe(this){
            when(it){
                is BroadListState.UnInitialized -> {initViews()}
                is BroadListState.Loading -> {}
                is BroadListState.Success -> handleSuccessState(it)
                is BroadListState.Error -> handleErrorState()
            }
        }
    }

    private fun initViews() {
        binding.FragmentBroadListProgressBar.isVisible = true
        binding.FragmentBroadListEmptyTextView.isVisible = false

        binding.FragmentBroadListRecyclerView.adapter = adapter
        binding.FragmentBroadListRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun getBroadCastListByNumber(num: String){
        viewModel.getBroadCastList(num)
    }

    private fun handleSuccessState(state: BroadListState.Success) {
        binding.FragmentBroadListProgressBar.isVisible = false

        adapter.broadList = state.list.broad
        if (state.list.broad.isEmpty()){
            binding.FragmentBroadListEmptyTextView.isVisible = true
        }
        adapter.notifyDataSetChanged()
    }

    private fun handleErrorState() {
        binding.FragmentBroadListProgressBar.isVisible = false
        binding.FragmentBroadListEmptyTextView.isVisible = false
        Toast.makeText(context, "에러 발생", Toast.LENGTH_SHORT).show()
    }
}