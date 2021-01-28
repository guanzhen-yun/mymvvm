package com.ziroom.mvvm.app

import com.aleyn.mvvm.app.GlobalConfig

object MVVMManager {

    private val DEFULT = object : GlobalConfig {}

    private var mConfig: GlobalConfig = DEFULT

    fun install(config: GlobalConfig) {
        mConfig = config
    }

    fun getConfig() = mConfig
}