package com.ziroom.mymvvm.ui.me

import androidx.lifecycle.MutableLiveData
import com.ziroom.mvvm.base.BaseViewModel
import com.ziroom.mymvvm.network.entity.UsedWeb
import com.ziroom.mymvvm.utils.InjectorUtil

/**
 *   @auther : Aleyn
 *   time   : 2019/11/14
 */
class MeViewModel : BaseViewModel() {

    private val homeRepository by lazy { InjectorUtil.getHomeRepository() }

    var popularWeb = MutableLiveData<MutableList<UsedWeb>>()

    fun getPopularWeb() {
        launchGo({
            val result = homeRepository.getPopularWeb()
            if (result.isSuccess()) {
                popularWeb.value = result.data
            }
        })
    }
}