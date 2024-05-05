package com.mobile.ap_viewer.di

import android.content.Context
import com.mobile.ap_viewer.data.LocationManagerDataSource
import com.mobile.ap_viewer.data.WiFiManagerDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWiFiManagerDataSource(@ApplicationContext context: Context): WiFiManagerDataSource {
        return WiFiManagerDataSource(context)
    }

    //@Provides
    //@Singleton
    //fun provideLocationManagerDataSource(@ApplicationContext context: Context): LocationManagerDataSource {
    //    return LocationManagerDataSource(context)
    //}
}