package com.habitsapp.authentication.di

import com.habitsapp.authentication.data.matcher.EmailMatcherImpl
import com.habitsapp.authentication.data.repository.AuthenticationRepositoryImpl
import com.habitsapp.authentication.domain.matcher.EmailMatcher
import com.habitsapp.authentication.domain.repository.AuthenticationRepository
import com.habitsapp.authentication.domain.usecase.GetUserIdUseCase
import com.habitsapp.authentication.domain.usecase.LoginUseCase
import com.habitsapp.authentication.domain.usecase.LoginUseCases
import com.habitsapp.authentication.domain.usecase.LogoutUseCase
import com.habitsapp.authentication.domain.usecase.SignUpUseCase
import com.habitsapp.authentication.domain.usecase.SignUpUseCases
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

    @Provides
    @Singleton
    fun provideSignUpUseCases(
        repository: AuthenticationRepository,
        emailMatcher: EmailMatcher
    ): SignUpUseCases {
        return SignUpUseCases(
            signUpUseCase = SignUpUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideGetUserIdUseCase(repository: AuthenticationRepository): GetUserIdUseCase {
        return GetUserIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: AuthenticationRepository): LogoutUseCase {
        return LogoutUseCase(repository)
    }
}