package com.github.cesar1287.cstv.di

import com.github.cesar1287.cstv.features.home.data.HomeRepository
import com.github.cesar1287.cstv.features.home.data.HomeRepositoryImpl
import com.github.cesar1287.cstv.features.matchdetail.data.MatchDetailRepository
import com.github.cesar1287.cstv.features.matchdetail.data.MatchDetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMatchDetailRepository(
        matchDetailRepository: MatchDetailRepositoryImpl
    ): MatchDetailRepository

    @Binds
    abstract fun bindHomeRepository(
        homeRepository: HomeRepositoryImpl
    ): HomeRepository
}