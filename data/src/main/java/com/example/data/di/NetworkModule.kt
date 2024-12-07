package com.example.data.di

import com.example.data.remote.apis.APIConstants.Companion.API_KEY
import com.example.data.remote.apis.APIConstants.Companion.BASE_URL
import com.example.data.remote.apis.APIConstants.Companion.PARAM_API_KEY
import com.example.data.remote.apis.APIConstants.Companion.TAG_OK_HTTP
import com.example.data.remote.apis.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): WeatherAPI = retrofit.create(WeatherAPI::class.java)

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideConvertFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @AuthInterceptor authInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor { message ->
        Timber.tag(TAG_OK_HTTP).e(message)
    }.setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    @Singleton
    @AuthInterceptor
    fun provideAuthInterceptor() = Interceptor { chain ->
        var request = chain.request()
        val url =
            request
                .url
                .newBuilder()
                .addQueryParameter(PARAM_API_KEY, API_KEY)
                .build()
        request = request
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptor
