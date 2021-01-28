package com.ziroom.mvvm.base

/**
 * 响应体
 */
interface IBaseResponse<T> {
    fun code(): Int
    fun msg(): String
    fun data(): T
    fun isSuccess(): Boolean
}