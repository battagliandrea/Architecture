package com.ab21.data.di

import com.ab21.data.repository.PokemonRepository
import com.ab21.domain.repository.IPokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providePokemonRepository(repository: PokemonRepository): IPokemonRepository
}
