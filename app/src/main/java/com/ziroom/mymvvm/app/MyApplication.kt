package com.ziroom.mymvvm.app

import com.aleyn.mvvm.base.BaseApplication
import com.blankj.utilcode.BuildConfig
import com.blankj.utilcode.util.LogUtils

class MyApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        LogUtils.getConfig().run {
            isLogSwitch = BuildConfig.DEBUG
            setSingleTagSwitch(true)
        }
    }
}