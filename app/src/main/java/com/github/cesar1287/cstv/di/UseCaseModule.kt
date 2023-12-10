package com.github.cesar1287.cstv.di

import com.github.cesar1287.cstv.features.home.domain.HomeUseCase
import com.github.cesar1287.cstv.features.home.domain.HomeUseCaseImpl
import com.github.cesar1287.cstv.features.matchdetail.domain.MatchDetailUseCase
import com.github.cesar1287.cstv.features.matchdetail.domain.MatchDetailUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindMatchDetailUseCase(
        matchDetailUseCase: MatchDetailUseCaseImpl
    ): MatchDetailUseCase

    @Binds
    abstract fun bindHomeUseCase(
        homeUseCase: HomeUseCaseImpl
    ): HomeUseCase
}