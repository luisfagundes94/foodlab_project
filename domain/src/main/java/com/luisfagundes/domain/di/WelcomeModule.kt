package com.luisfagundes.domain.di

import com.luisfagundes.domain.repositories.WelcomeRepository
import com.luisfagundes.domain.usecases.welcome.ReadOnBoarding
import com.luisfagundes.domain.usecases.welcome.SaveOnBoarding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class WelcomeModule {
    @ViewModelScoped
    @Provides
    fun provideSaveOnBoarding(repository: WelcomeRepository) = SaveOnBoarding(repository)

    @ViewModelScoped
    @Provides
    fun provideReadOnBoarding(repository: WelcomeRepository) = ReadOnBoarding(repository)
}