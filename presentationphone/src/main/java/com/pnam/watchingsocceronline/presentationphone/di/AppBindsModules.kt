package com.pnam.watchingsocceronline.presentationphone.di

import com.pnam.watchingsocceronline.presentationphone.usecase.LibraryMainUseCase
import com.pnam.watchingsocceronline.presentationphone.usecase.MainContainerUseCase
import com.pnam.watchingsocceronline.presentationphone.usecase.impl.DefaultLibraryMainUseCaseImpl
import com.pnam.watchingsocceronline.presentationphone.usecase.impl.DefaultMainContainerUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModules {
    @Binds
    abstract fun getLibraryMainUseCase(useCase: DefaultLibraryMainUseCaseImpl): LibraryMainUseCase

    @Binds
    abstract fun getMainContainerUseCase(useCase: DefaultMainContainerUseCaseImpl): MainContainerUseCase
}