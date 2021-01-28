package com.ziroom.mymvvm.app.base

import com.ziroom.mvvm.base.IBaseResponse

data class BaseResult<T>(
    val errorMsg: String,
    val errorCode: Int,
    val data: T
) : IBaseResponse<T> {

    override fun code() = errorCode

    override fun msg() = errorMsg

    override fun data() = data

    override fun isSuccess() = errorCode == 0

}