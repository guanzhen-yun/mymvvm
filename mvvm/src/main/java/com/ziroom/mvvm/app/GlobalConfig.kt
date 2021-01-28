package com.aleyn.mvvm.app

import androidx.lifecycle.ViewModelProvider
import com.ziroom.mvvm.base.ViewModelFactory
import com.ziroom.mvvm.network.ExceptionHandle

/**
 * 全局配置
 */
interface GlobalConfig {

    fun viewModelFactory(): ViewModelProvider.Factory? = ViewModelFactory.getInstance()

    fun globalExceptionHandle(e: Throwable) = ExceptionHandle.handleException(e)

}