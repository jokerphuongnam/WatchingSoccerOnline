package com.pnam.watchingsocceronline.presentationphone.di

import com.pnam.watchingsocceronline.presentationphone.usecase.*
import com.pnam.watchingsocceronline.presentationphone.usecase.impl.*
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

    @Binds
    abstract fun getDownloadUseCase(useCase: DefaultDownloadUseCaseImpl): DownloadUseCase

    @Binds
    abstract fun getMainUseCase(useCase: DefaultMainUseCaseImpl): MainUseCase

    @Binds
    abstract fun getHomeUseCase(useCase: DefaultHomeUseCaseImpl): HomeUseCase

    @Binds
    abstract fun getChartUseCase(useCase: DefaultChartUseCaseImpl): ChartUseCase

    @Binds
    abstract fun getCommentsUseCase(useCase: DefaultCommentsUseCaseImpl): CommentsUseCase

    @Binds
    abstract fun getSignInUseCase(useCase: DefaultSignInUseCaseImpl): SignInUseCase

    @Binds
    abstract fun getWriteCommentUseCase(useCase: DefaultWriteCommentUseCaseImpl): WriteCommentUseCase

    @Binds
    abstract fun getUserUseCase(useCase: DefaultUserUseCaseImpl): UserUseCase

    @Binds
    abstract fun getRegisterUseCase(useCase: DefaultRegisterUseCaseImpl): RegisterUseCase

    @Binds
    abstract fun getEditProfileUseCase(useCase: DefaultEditProfileUseCaseImpl): EditProfileUseCase

    @Binds
    abstract fun getChangePassword(useCase: DefaultChangePasswordUseCaseImpl): ChangePasswordUseCase
}