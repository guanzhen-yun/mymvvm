package com.ziroom.mymvvm.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blankj.utilcode.util.Utils
import com.ziroom.mymvvm.data.db.dao.HomeDao
import com.ziroom.mymvvm.data.db.migration.MIGRATION
import com.ziroom.mymvvm.network.entity.BannerBean
import com.ziroom.mymvvm.network.entity.HomeListBean

@Database(entities = [HomeListBean::class, BannerBean::class], version = 2, exportSchema = false)
abstract class LinDatabase : RoomDatabase() {

    abstract fun homeLocaData(): HomeDao


    companion object {
        fun getInstanse() = SingletonHolder.INSTANCE
    }

    private object SingletonHolder {
        val INSTANCE = Room.databaseBuilder(
            Utils.getApp(),
            LinDatabase::class.java,
            "lin_db"
        )
            .addMigrations(MIGRATION.MIGRATION_1_2)
            .build()
    }
}