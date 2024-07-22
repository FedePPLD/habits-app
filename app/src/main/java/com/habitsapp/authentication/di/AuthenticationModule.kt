package com.habitsapp.authentication.di

import com.habitsapp.authentication.data.matcher.EmailMatcherImpl
import com.habitsapp.authentication.data.repository.AuthenticationRepositoryImpl
import com.habitsapp.authentication.domain.matcher.EmailMatcher
import com.habitsapp.authentication.domain.repository.AuthenticationRepository
import com.habitsapp.authentication.domain.usecase.LoginUseCase
import com.habitsapp.authentication.domain.usecase.LoginUseCases
import com.habitsapp.authentication.domain.usecase.ValidateEmailUseCase
import com.habitsapp.authentication.domain.usecase.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {
    @Provides
    @Singleton
    fun providesAuthenticationRepository(): AuthenticationRepository {
        return AuthenticationRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: AuthenticationRepository,
        emailMatcher: EmailMatcher
    ): LoginUseCases {
        return LoginUseCases(
            loginUseCase = LoginUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase()
        )
    }
}