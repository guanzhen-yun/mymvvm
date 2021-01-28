package com.ziroom.mymvvm.ui.home

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.ziroom.mvvm.base.BaseViewModel
import com.ziroom.mymvvm.network.entity.BannerBean
import com.ziroom.mymvvm.network.entity.HomeListBean
import com.ziroom.mymvvm.utils.InjectorUtil

class HomeViewModel : BaseViewModel() {
    private val homeRepository by lazy { InjectorUtil.getHomeRepository() }

    private val mBanners = MutableLiveData<List<BannerBean>>()

    private val projectData = MutableLiveData<HomeListBean>()

    fun getBanner(refresh: Boolean = false): MutableLiveData<List<BannerBean>> {
        launchGo({
            mBanners.value = homeRepository.getBannerData(refresh)
        })
        return mBanners
    }

    /**
     * @param page 页码
     * @param refresh 是否刷新
     */
    fun getHomeList(page: Int, refresh: Boolean = false): MutableLiveData<HomeListBean> {
        launchGo({
            projectData.value = homeRepository.getHomeList(page, refresh)
        }, {
            projectData.value = null
        })
        return projectData
    }
}