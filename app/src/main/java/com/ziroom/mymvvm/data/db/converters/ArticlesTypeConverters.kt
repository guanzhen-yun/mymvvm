package com.ziroom.mymvvm.data.db.converters

import androidx.room.TypeConverter
import com.blankj.utilcode.util.GsonUtils
import com.google.gson.reflect.TypeToken
import com.ziroom.mymvvm.network.entity.ArticlesBean

class ArticlesTypeConverters {
    @TypeConverter
    fun stringToArticles(json: String): List<ArticlesBean> {
        val type = object : TypeToken<List<ArticlesBean>>() {}.type
        return GsonUtils.fromJson(json, type)
    }

    @TypeConverter
    fun articlesToString(data: List<ArticlesBean>): String = GsonUtils.toJson(data)
}