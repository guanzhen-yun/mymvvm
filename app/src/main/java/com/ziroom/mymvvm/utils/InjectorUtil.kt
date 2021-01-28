package com.ziroom.mymvvm.utils

import com.ziroom.mymvvm.data.HomeRepository
import com.ziroom.mymvvm.data.db.LinDatabase
import com.ziroom.mymvvm.data.http.HomeNetWork

object InjectorUtil {
    fun getHomeRepository() = HomeRepository.getInstance(
        HomeNetWork.getInstance(),
        LinDatabase.getInstanse().homeLocaData()
    )
}