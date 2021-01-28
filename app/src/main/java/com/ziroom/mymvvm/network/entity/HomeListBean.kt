package com.ziroom.mymvvm.network.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ziroom.mymvvm.data.db.converters.ArticlesTypeConverters

@Entity(tableName = "home_data")
@TypeConverters(ArticlesTypeConverters::class)
data class HomeListBean(
    @PrimaryKey
    val curPage: Int,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int,
    val datas: MutableList<ArticlesBean>
)


data class NavTypeBean(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)

data class UsedWeb(
    val icon: String,
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)