package com.habitsapp.home.di

import com.habitsapp.home.data.HomeRepositoryImpl
import com.habitsapp.home.domain.home.usecase.CompleteHabitUseCase
import com.habitsapp.home.domain.home.usecase.GetAllHabitsForSelectedDateUseCase
import com.habitsapp.home.domain.home.usecase.HomeUseCases
import com.habitsapp.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    @Singleton
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases {
        return HomeUseCases(
            completeHabitUseCase = CompleteHabitUseCase(repository),
            getAllHabitsForSelectedDateUseCase = GetAllHabitsForSelectedDateUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideHomeRepository(): HomeRepository {
        return HomeRepositoryImpl()
    }
}