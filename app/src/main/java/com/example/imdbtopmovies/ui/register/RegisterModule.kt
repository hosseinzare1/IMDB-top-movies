package com.example.imdbtopmovies.ui.register

import com.example.imdbtopmovies.models.register.RegisterBody
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegisterModule {

    @Provides
    @Singleton
    fun provideUserBody(): RegisterBody = RegisterBody()


}