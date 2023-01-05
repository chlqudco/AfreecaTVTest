package com.chlqudco.afreecatvtest.presentation.detail

import android.os.Bundle
import com.bumptech.glide.Glide
import com.chlqudco.afreecatvtest.data.response.Broad
import com.chlqudco.afreecatvtest.databinding.ActivityDetailBinding
import com.chlqudco.afreecatvtest.presentation.base.BaseActivity
import org.koin.android.ext.android.inject

internal class DetailActivity : BaseActivity<DetailViewModel, ActivityDetailBinding>() {

    override val viewModel by inject<DetailViewModel>()

    override fun getViewBinding() = ActivityDetailBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val broad = intent.getParcelableExtra<Broad>("broad")

        broad?.let {
            Glide.with(this@DetailActivity)
                .load("https:${broad.profileImg}")
                .into(binding.ActivityDetailProfileImageView)

            binding.ActivityDetailIdTextView.text = broad.userId
            binding.ActivityDetailNickTextView.text = broad.userNick

            Glide.with(this@DetailActivity)
                .load("https:${broad.broadThumb}")
                .into(binding.ActivityDetailThumbImageView)
        }

    }
}