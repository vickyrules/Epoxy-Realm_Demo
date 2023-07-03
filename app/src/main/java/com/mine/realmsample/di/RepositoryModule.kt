package com.mine.realmsample.di

import com.mine.realmsample.data.repository.MainRepository
import com.mine.realmsample.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {
    @Binds
    fun bindsNewsRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ):MainRepository
}