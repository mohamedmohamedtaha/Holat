package com.holat.login.hilt

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.holat.login.data.datastore.DataStoreRepository
import com.holat.login.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
const val IMAGE_URL= "https://con.samacares.sa/app-files/"
//const val BASE_URL ="https://con.samacares.sa/hollat_sama/public/"
const val BASE_URL ="https://back-dev.hollat.net/hollat_upgrade_develop/public/"
//const val BASE_URL= "https://care.ia.gov.sa/hollat_upgrade/public/"
/**
Created by Mohamed Mohamed Taha on 11/12/2023
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideGsonConvertorFactory(gson: Gson):GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideLogging():HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOriginalInterceptor(repository: DataStoreRepository) = Interceptor{ chain->
        val originalRequest = chain.request()
        val isAuthorize = originalRequest.headers["isAuthorize"] == "true"
        val requestBuilder = originalRequest.newBuilder()
           .addHeader("Accept", "application/json")
           .removeHeader("isAuthorize")
        if (isAuthorize){
            val token = runBlocking { repository.getToken().first() }
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        chain.proceed(requestBuilder.build())
    }
    @Provides
    @Singleton
    fun provideTrustAllCertificateHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        loginInterceptor: Interceptor,
    ): OkHttpClient {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts: Array<TrustManager> = arrayOf(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?,
                ) = Unit

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?,
                ) = Unit

                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            }
        )
        // Install the all-trusting trust manager
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory


        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            //.authenticator(TokenAuthenticator2())
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(loginInterceptor)
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
        return try {
            okHttpClient.build()
        } catch (e: Exception) {
            okHttpClient.build()
        }
    }


//    @Provides
//    @Singleton
//    fun provideUnSafeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
//                                  loginInterceptor: Interceptor):OkHttpClient{
//        val okHttpClient = OkHttpClient.Builder()
//        okHttpClient.readTimeout(600,TimeUnit.SECONDS)
//            .connectTimeout(600,TimeUnit.SECONDS)
//            .addInterceptor(httpLoggingInterceptor)
//            .addInterceptor(loginInterceptor)
//        return try {
//            okHttpClient.build()
//        }catch (e:Exception){
//            okHttpClient.build()
//        }
//    }
//    @Provides
//    @Singleton
//    fun provideSafeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
//                                loginInterceptor: Interceptor,
//                                @ApplicationContext context: Context):OkHttpClient{
//        val certificateFactory = CertificateFactory.getInstance("X.509")
//    //Production
////    val certificate = context.resources.openRawResource(R.raw.samacares_sa)
//   // val certificate = context.resources.openRawResource(R.raw.uat_ia_gov_sa)
//    //Develop
//    val certificate = context.resources.openRawResource(R.raw.back_hollat_net)
//        val x509Certificate = certificate.use { certificateFactory.generateCertificate(it) as X509Certificate }
//        //Create a keystore containing our trusted CAs
//        val keyStoreType = KeyStore.getDefaultType()
//        val keyStore = KeyStore.getInstance(keyStoreType).apply {
//            load(null,null)
//            setCertificateEntry("ca",x509Certificate)
//        }
//        //Create a TrustManager that trusted the CAs input stream our keystore
//        val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
//        val tmf = TrustManagerFactory.getInstance(tmfAlgorithm).apply { init(keyStore) }
//
//        //Create an SSLContext that uses our TrustManager
//        val sslContext = SSLContext.getInstance("TLS").apply { init(null, tmf.trustManagers,null) }
//        //Create an ssl socket factory with our all trusting manager
//        val sslSocketFactory = sslContext.socketFactory
//        val okHttpClient = OkHttpClient.Builder()
//        okHttpClient.readTimeout(600,TimeUnit.SECONDS)
//            .connectTimeout(600,TimeUnit.SECONDS)
//            .addInterceptor(httpLoggingInterceptor)
//            .addInterceptor(loginInterceptor)
//            .sslSocketFactory(sslSocketFactory = sslSocketFactory, trustManager = tmf.trustManagers[0]as X509TrustManager)
//        return try {
//            okHttpClient.build()
//        }catch (e:Exception){
//            okHttpClient.build()
//        }
//    }
    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrl() = BASE_URL
        //Production "https://care.ia.gov.sa/hollat_upgrade/public/"
    //Develop "https://back-dev.hollat.net/hollat_upgrade_develop/public/"
    // Sama Care https://con.samacares.sa/hollat_sama/public/
    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,
                        okHttpClient: OkHttpClient,@Named("baseUrl")baseUrl:String):Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideApis(retrofit: Retrofit) = retrofit.create(ApiServices::class.java)
}