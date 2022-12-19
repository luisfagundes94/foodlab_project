package com.luisfagundes.data.di

import com.luisfagundes.data.BuildConfig
import com.luisfagundes.data.datasources.RemoteRecipeDataSource
import com.luisfagundes.data.interceptors.AuthInterception
import com.luisfagundes.data.repositories.RecipeRepositoryImpl
import com.luisfagundes.data.services.ApiService
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.domain.repositories.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIME_OUT = 15L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ) = createApiService(retrofit)

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit = createRetrofit(okHttpClient, converterFactory)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterception: AuthInterception,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = createHttpClient(authInterception, httpLoggingInterceptor)

    @Provides
    @Singleton
    fun provideAuthInterceptor() = createAuthInterceptor()

    @Provides
    @Singleton
    fun provideHttpInterceptor() = createHttpInterceptor()

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRemoteRecipeDataSource(
        apiService: ApiService
    ): RecipeDataSource = RemoteRecipeDataSource(apiService)

    @Provides
    @Singleton
    fun provideRecipeRepository(
        remoteDataSource: RecipeDataSource
    ): RecipeRepository = RecipeRepositoryImpl(remoteDataSource)

}

private fun createApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

private fun createRetrofit(
    okHttpClient: OkHttpClient,
    converterFactory: GsonConverterFactory
) = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(converterFactory)
    .build()

private fun createHttpClient(
    authInterception: AuthInterception,
    httpLoggingInterceptor: HttpLoggingInterceptor
) =
    OkHttpClient.Builder()
        .addInterceptor(authInterception)
        .addInterceptor(httpLoggingInterceptor)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()

private fun createAuthInterceptor() = AuthInterception(apiKey = BuildConfig.API_KEY)

private fun createHttpInterceptor() = HttpLoggingInterceptor().apply {
    setLevel(
        when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    )
}
