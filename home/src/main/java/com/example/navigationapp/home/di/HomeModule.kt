package com.example.navigationapp.home.di

import com.example.navigationapp.home.HomeNavigationImpl
import com.example.navigationapp.home.api.HomeNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal interface HomeModule {
    @Binds
    fun HomeNavigationImpl.bindHomeNavigation(): HomeNavigation
}