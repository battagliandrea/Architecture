package com.battman.data.sample.di

import com.battman.data.sample.api.repositories.ISampleRepository
import com.battman.data.sample.impl.repositories.SampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindSampleRepository(repository: SampleRepository): ISampleRepository
}
