package com.ziroom.mymvvm.ui.project

import android.content.Intent
import android.os.Bundle
import com.ziroom.mvvm.base.BaseFragment
import com.ziroom.mvvm.event.Message
import com.ziroom.mymvvm.R
import com.ziroom.mymvvm.databinding.ProjectFragmentBinding
import com.ziroom.mymvvm.network.entity.ArticlesBean
import com.ziroom.mymvvm.ui.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class ProjectFragment : BaseFragment<ProjectViewModel, ProjectFragmentBinding>() {


    companion object {
        fun newInstance() = ProjectFragment()
    }

    override fun layoutId() = R.layout.project_fragment

    override fun initView(savedInstanceState: Bundle?) {
        mBinding?.viewModel = viewModel
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun lazyLoadData() {
        viewModel.getFirstData()
    }

    override fun handleEvent(msg: Message) {
        when (msg.code) {
            0 -> {
                val bean = msg.obj as ArticlesBean
                val intent = Intent().apply {
                    setClass(activity!!, DetailActivity::class.java)
                    putExtra("url", bean.link)
                }
                startActivity(intent)
            }
        }
    }
}
