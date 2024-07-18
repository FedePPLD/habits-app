package com.habitsapp.onboarding.di

import android.content.Context
import android.content.SharedPreferences
import com.habitsapp.onboarding.data.repository.OnboardingRepositoryImpl
import com.habitsapp.onboarding.domain.repository.OnboardinRepository
import com.habitsapp.onboarding.domain.usecase.CompleteOnboardingUseCase
import com.habitsapp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OnboardingModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("habits_onboarding_preference", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(sharedPreferences: SharedPreferences): OnboardinRepository {
        return OnboardingRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideHasSeenOnboardingUseCase(repository: OnboardinRepository): HasSeenOnboardingUseCase {
        return HasSeenOnboardingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCompleteOnboardingUseCase(repository: OnboardinRepository): CompleteOnboardingUseCase {
        return CompleteOnboardingUseCase(repository)
    }
}