package com.hliosdja.experimental.di

import com.hliosdja.experimental.ui.navigation.AppNavigator
import com.hliosdja.experimental.ui.navigation.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
fun interface AppModule {

    @Singleton
    @Binds
    fun bindAppNavigator(impl: AppNavigatorImpl): AppNavigator
}