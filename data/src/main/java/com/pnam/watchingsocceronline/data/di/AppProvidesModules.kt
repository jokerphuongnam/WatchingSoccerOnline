package com.pnam.watchingsocceronline.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pnam.watchingsocceronline.data.database.local.AppDatabase
import com.pnam.watchingsocceronline.data.database.local.DownloadLocal
import com.pnam.watchingsocceronline.data.database.local.NotificationLocal
import com.pnam.watchingsocceronline.data.database.local.UserLocal
import com.pnam.watchingsocceronline.data.database.network.NetworkConnectionInterceptor
import com.pnam.watchingsocceronline.data.database.network.impl.RetrofitSearchNetworkImpl
import com.pnam.watchingsocceronline.data.database.network.impl.RetrofitUserNetworkImpl
import com.pnam.watchingsocceronline.data.database.network.impl.RetrofitVideoNetworkImpl
import com.pnam.watchingsocceronline.data.utils.RetrofitUtils.BASE_URL
import com.pnam.watchingsocceronline.data.utils.RetrofitUtils.TIMEOUT
import com.pnam.watchingsocceronline.data.utils.RoomUtils.DB_NAME
import com.pnam.watchingsocceronline.domain.util.HH_MM_DD_MM_YYYY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppProvidesModules {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideDownloadLocal(database: AppDatabase): DownloadLocal = database.getDownloadDao()

    @Provides
    @Singleton
    fun provideUserLocal(database: AppDatabase): UserLocal = database.getUserDao()

    @Provides
    @Singleton
    fun provideNotificationLocal(database: AppDatabase): NotificationLocal =
        database.getNotificationDao()

    @Provides
    @Singleton
    fun providerGson(): Gson =
        GsonBuilder().setDateFormat(HH_MM_DD_MM_YYYY).create()

    @Provides
    @Singleton
    fun providerGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun providerLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun providerOkHttp(
        interceptor: NetworkConnectionInterceptor,
        logging: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttp: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttp)
        .build()

    @Provides
    @Singleton
    fun providerUserService(retrofit: Retrofit): RetrofitUserNetworkImpl.UserService =
        retrofit.create(RetrofitUserNetworkImpl.UserService::class.java)

    @Provides
    @Singleton
    fun providerVideoService(retrofit: Retrofit): RetrofitVideoNetworkImpl.VideoService =
        retrofit.create(RetrofitVideoNetworkImpl.VideoService::class.java)

    @Provides
    @Singleton
    fun providerSearchService(retrofit: Retrofit): RetrofitSearchNetworkImpl.SearchService =
        retrofit.create(RetrofitSearchNetworkImpl.SearchService::class.java)
}