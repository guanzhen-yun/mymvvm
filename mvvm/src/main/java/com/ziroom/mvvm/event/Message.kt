package com.ziroom.mvvm.event

/**
 * @JvmOverloads 声明重载构造函数
 */
class Message @JvmOverloads constructor(
    var code: Int = 0,
    var msg: String = "",
    var arg1: Int = 0,
    var arg2: Int = 0,
    var obj: Any? = null
)