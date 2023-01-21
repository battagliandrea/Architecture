package com.ab21.data.network.env.di

import com.ab21.core.annotations.Flavor
import com.ab21.data.env.Environment
import com.ab21.data.network.env.BuildConfig
import com.ab21.data.network.env.Environments
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkEnvModule {

    @Provides
    @Flavor
    fun versionName(): String = BuildConfig.FLAVOR

    @Provides
    @Singleton
    fun environment(
        @Flavor flavor: String
    ): Environment =
        Environments.fromName(flavor)
}
