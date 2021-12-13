package com.wangxingxing.networkoptimizing.net

import android.content.Context
import java.lang.Exception
import java.security.KeyStore
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory

/**
 * author : 王星星
 * date : 2021/11/27 16:46
 * email : 1099420259@qq.com
 * description :
 */
object SSLSocketFactoryUtils {
    private const val CLIENT_PRI_KEY = "client.bks"
    private const val TRUSTSTORE_PUB_KEY = "truststore.bks"
    private const val CLIENT_BKS_PASSWORD = "123456"
    private const val TRUSTSTORE_BKS_PASSWORD = "123456"
    private const val KEYSTORE_TYPE = "BKS"
    private const val PROTOCOL_TYPE = "TLS"
    private const val CERTIFICATE_FORMAT = "X509"

    fun getSSLCertification(context: Context): SSLSocketFactory? {
        var sslSocketFactory: SSLSocketFactory? = null
        try {
            // 服务器端需要验证的客户端证书，其实就是客户端的keystore
            val keyStore = KeyStore.getInstance(KEYSTORE_TYPE) // 客户端信任的服务器端证书
            val trustStore = KeyStore.getInstance(KEYSTORE_TYPE) //读取证书
            val ksIn = context.assets.open(CLIENT_PRI_KEY)
            val tsIn = context.assets.open(TRUSTSTORE_PUB_KEY) //加载证书
            keyStore.load(ksIn, CLIENT_BKS_PASSWORD.toCharArray())
            trustStore.load(tsIn, TRUSTSTORE_BKS_PASSWORD.toCharArray())
            ksIn.close()
            tsIn.close()
            //初始化SSLContext
            val sslContext = SSLContext.getInstance(PROTOCOL_TYPE)
            val trustManagerFactory = TrustManagerFactory.getInstance(CERTIFICATE_FORMAT)
            val keyManagerFactory = KeyManagerFactory.getInstance(CERTIFICATE_FORMAT)
            trustManagerFactory.init(trustStore)
            keyManagerFactory.init(keyStore, CLIENT_BKS_PASSWORD.toCharArray())
            sslContext.init(keyManagerFactory.keyManagers, trustManagerFactory.trustManagers, null)
            return sslContext.socketFactory
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sslSocketFactory
    }
}