package com.luisfagundes.di

import android.content.Context
import com.luisfagundes.data.datasources.RemoteRecipeDataSource
import com.luisfagundes.data.repositories.RecipeRepositoryImpl
import com.luisfagundes.data.repositories.WelcomeRepositoryImpl
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.domain.repositories.WelcomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext appContext: Context) = appContext

    @Provides
    @Singleton
    fun provideWelcomeRepository(
        appContext: Context
    ): WelcomeRepository = WelcomeRepositoryImpl(appContext)

}