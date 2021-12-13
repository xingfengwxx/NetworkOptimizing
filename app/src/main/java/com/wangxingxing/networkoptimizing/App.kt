package com.wangxingxing.networkoptimizing

import android.app.Application

/**
 * author : 王星星
 * date : 2021/11/27 16:28
 * email : 1099420259@qq.com
 * description :
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    companion object {
        var instance: App? = null
        fun instance() = instance!!
    }
}