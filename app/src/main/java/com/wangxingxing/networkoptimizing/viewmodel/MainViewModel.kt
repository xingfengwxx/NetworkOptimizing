package com.wangxingxing.networkoptimizing.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.wangxingxing.networkoptimizing.net.RetrofitClient
import com.wangxingxing.networkoptimizing.net.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * author : 王星星
 * date : 2021/11/27 16:59
 * email : 1099420259@qq.com
 * description :
 */
class MainViewModel : ViewModel() {

    private val TAG = "MainViewModel"

    fun list() =
        flow {
            val userApi = RetrofitClient.createApi(UserApi::class.java)
            emit(userApi.userInfo())
        }.flowOn(Dispatchers.IO).catch { e ->
            e.printStackTrace()
            Log.e(TAG, e.message ?: "exception caught")
        }
}