package com.wangxingxing.networkoptimizing.net

import android.util.Log
import com.wangxingxing.networkoptimizing.App
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.internal.http.BridgeInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * author : 王星星
 * date : 2021/11/27 17:01
 * email : 1099420259@qq.com
 * description :
 */
object RetrofitClient {

    private const val BASE_URL = "http://121.4.214.140:8080/HelloWorldWeb_war/"

    private const val TAG = "RetrofitClient"

    private val instance: Retrofit by lazy {
        val interceptor = HttpLoggingInterceptor {
            Log.d(TAG, it)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
//            .addInterceptor(BridgeInterceptor())
//            .sslSocketFactory(SSLSocketFactoryUtils.getSSLCertification(App.instance()))
//            .hostnameVerifier { _, _ -> true }
//            .protocols(listOf(Protocol.H2_PRIOR_KNOWLEDGE))
            .build()

        Retrofit.Builder()
            //.baseUrl("https://192.168.0.118:9999/optimizing/")  // https
            .baseUrl(BASE_URL) // http
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createApi(clazz: Class<T>): T {
        return instance.create(clazz) as T
    }
}