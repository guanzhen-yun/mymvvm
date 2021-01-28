package com.ziroom.mymvvm.data.http

import com.ziroom.mymvvm.network.api.HomeService
import com.ziroom.mymvvm.utils.RetrofitClient

//http网络任务
class HomeNetWork {
    private val mService by lazy { RetrofitClient.getInstance().create(HomeService::class.java) }

    suspend fun getBannerData() = mService.getBanner()

    suspend fun getHomeList(page: Int) = mService.getHomeList(page)

    suspend fun getNaviJson() = mService.naviJson()

    suspend fun getProjectList(page: Int, cid: Int) = mService.getProjectList(page, cid)

    suspend fun getPopularWeb() = mService.getPopularWeb()

    //伴生对象
    companion object {
        @Volatile
        private var netWork: HomeNetWork? = null

        fun getInstance() = netWork ?: synchronized(this) {
            netWork ?: HomeNetWork().also { netWork = it }
        }
    }
}