package com.wangxingxing.networkoptimizing.net

import com.wangxingxing.networkoptimizing.model.User
import retrofit2.http.GET

/**
 * author : 王星星
 * date : 2021/11/27 16:57
 * email : 1099420259@qq.com
 * description :
 */
interface UserApi {

    @GET("userInfo")
    suspend fun userInfo() : User
}