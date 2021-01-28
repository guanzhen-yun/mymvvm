package com.ziroom.mymvvm.ui.me

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziroom.mvvm.base.BaseFragment
import com.ziroom.mymvvm.R
import com.ziroom.mymvvm.databinding.MeFragmentBinding
import com.ziroom.mymvvm.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.me_fragment.*

class MeFragment : BaseFragment<MeViewModel, MeFragmentBinding>() {

    private val mAdapter by lazy { MeWebAdapter() }

    companion object {
        fun newInstance() = MeFragment()
    }

    override fun layoutId() = R.layout.me_fragment

    override fun initView(savedInstanceState: Bundle?) {
        with(rv_me_uesd_web) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
        viewModel.popularWeb.observe(viewLifecycleOwner, {
            mAdapter.setNewInstance(it)
        })
        mAdapter.setOnItemClickListener { _, _, position ->
            val intent = Intent().apply {
                setClass(activity!!, DetailActivity::class.java)
                putExtra("url", (mAdapter.data[position]).link)
            }
            startActivity(intent)
        }
    }

    override fun lazyLoadData() {
        viewModel.getPopularWeb()
    }
}
