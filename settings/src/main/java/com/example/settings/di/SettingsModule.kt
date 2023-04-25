package com.example.settings.di


import com.example.settings.SettingsNavGraphProviderImpl
import com.example.settings.api.SettingsNavGraphProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal interface SettingsModule {
    @Binds
    fun SettingsNavGraphProviderImpl.bindGraph(): SettingsNavGraphProvider
}