package com.ziroom.mymvvm.data

import com.ziroom.mvvm.base.BaseModel
import com.ziroom.mymvvm.app.base.BaseResult
import com.ziroom.mymvvm.data.db.dao.HomeDao
import com.ziroom.mymvvm.data.http.HomeNetWork
import com.ziroom.mymvvm.network.entity.BannerBean
import com.ziroom.mymvvm.network.entity.HomeListBean
import com.ziroom.mymvvm.network.entity.NavTypeBean
import com.ziroom.mymvvm.network.entity.UsedWeb

class HomeRepository private constructor(
    private val netWork: HomeNetWork,
    private val localData: HomeDao
) : BaseModel() {

    suspend fun getBannerData(refresh: Boolean = false): List<BannerBean> {
        return cacheNetCall({
            netWork.getBannerData()
        }, {
            localData.getBannerList()
        }, {
            if (refresh) localData.deleteBannerAll()
            localData.insertBanner(it)
        }, {
            !refresh && !it.isNullOrEmpty()
        })
    }

    suspend fun getHomeList(page: Int, refresh: Boolean): HomeListBean {
        return cacheNetCall({
            netWork.getHomeList(page)
        }, {
            localData.getHomeList(page + 1)
        }, {
            if (refresh) localData.deleteHomeAll()
            localData.insertData(it)
        }, {
            !refresh
        })
    }

    suspend fun getNaviJson(): BaseResult<List<NavTypeBean>> {
        return netWork.getNaviJson()
    }

    suspend fun getProjectList(page: Int, cid: Int): BaseResult<HomeListBean> {
        return netWork.getProjectList(page, cid)
    }

    suspend fun getPopularWeb(): BaseResult<MutableList<UsedWeb>> {
        return netWork.getPopularWeb()
    }

    companion object {

        @Volatile
        private var INSTANCE: HomeRepository? = null

        fun getInstance(netWork: HomeNetWork, homeDao: HomeDao) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: HomeRepository(netWork, homeDao).also { INSTANCE = it }
            }
    }
}