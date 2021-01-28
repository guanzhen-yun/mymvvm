package com.ziroom.mymvvm.network.api

import com.ziroom.mymvvm.app.base.BaseResult
import com.ziroom.mymvvm.network.entity.BannerBean
import com.ziroom.mymvvm.network.entity.HomeListBean
import com.ziroom.mymvvm.network.entity.NavTypeBean
import com.ziroom.mymvvm.network.entity.UsedWeb
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {
    /**
     * 玩安卓轮播图
     */
    @GET("banner/json")
    suspend fun getBanner(): BaseResult<List<BannerBean>>


    /**
     * 导航数据
     */
    @GET("project/tree/json")
    suspend fun naviJson(): BaseResult<List<NavTypeBean>>


    /**
     * 项目列表
     * @param page 页码，从0开始
     */
    @GET("article/listproject/{page}/json")
    suspend fun getHomeList(@Path("page") page: Int): BaseResult<HomeListBean>


    /**
     * 项目列表
     * @param page 页码，从0开始
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): BaseResult<HomeListBean>


    /**
     * 常用网站
     */
    @GET("friend/json")
    suspend fun getPopularWeb(): BaseResult<MutableList<UsedWeb>>
}