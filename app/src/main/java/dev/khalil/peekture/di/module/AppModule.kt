package dev.khalil.peekture.di.module

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dev.khalil.peekture.BuildConfig.BASE_URL
import dev.khalil.peekture.api.ApiService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    @Provides
    @Singleton
    fun providesOkHttpClient(application: Application): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = BODY

        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        val tenMb = 10 * 1024 * 1024L
        val cache = Cache(cacheDir, tenMb)

        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(20, SECONDS)
            .readTimeout(60, SECONDS)
            .writeTimeout(20, SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}