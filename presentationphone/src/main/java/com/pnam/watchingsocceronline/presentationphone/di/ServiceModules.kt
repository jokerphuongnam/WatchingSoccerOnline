package com.pnam.watchingsocceronline.presentationphone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ServiceComponent::class)
object ServiceModules {
    @Provides
    @ServiceScoped
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)
}