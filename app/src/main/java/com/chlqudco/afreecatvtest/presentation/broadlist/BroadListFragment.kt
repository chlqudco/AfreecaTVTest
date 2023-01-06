package com.chlqudco.afreecatvtest.presentation.broadlist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlqudco.afreecatvtest.databinding.FragmentBroadListBinding
import com.chlqudco.afreecatvtest.presentation.adapter.BroadListPagingAdapter
import com.chlqudco.afreecatvtest.presentation.base.BaseFragment
import com.chlqudco.afreecatvtest.presentation.main.MainActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

internal class BroadListFragment : BaseFragment<BroadListViewModel, FragmentBroadListBinding>() {

    //페이징 어댑터
    private val adapter by lazy { BroadListPagingAdapter( broadClickListener = {
        (activity as MainActivity).moveDetailActivity(it) })
    }

    //뷰모델 주입
    override val viewModel by inject<BroadListViewModel>()

    override fun getViewBinding() = FragmentBroadListBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        //어댑터 연결
        binding.FragmentBroadListRecyclerView.adapter = adapter
        binding.FragmentBroadListRecyclerView.layoutManager = LinearLayoutManager(context)

        //페이징 데이터 구독
        collectLatestStateFlow(viewModel.broadListPagingResult){
            adapter.submitData(it)
        }
    }

    //카테고리를 이용해 방송 목록 불러오기
    fun getBroadCastListByNumber(cate: String){
        viewModel.getBroadListByPaging(cate)
    }

    //페이징 데이터 구독 확장함수
    private fun <T> collectLatestStateFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(collect)
            }
        }
    }

    override fun observeData() {}
}