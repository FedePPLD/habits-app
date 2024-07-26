package com.habitsapp.home.di

import android.content.Context
import androidx.room.Room
import com.habitsapp.home.data.HomeDatabase
import com.habitsapp.home.data.local.HomeDao
import com.habitsapp.home.data.local.typeconverter.HomeTypeConverter
import com.habitsapp.home.data.repository.HomeRepositoryImpl
import com.habitsapp.home.domain.detail.usecase.DetailUseCases
import com.habitsapp.home.domain.detail.usecase.GetHabitByIdUseCase
import com.habitsapp.home.domain.detail.usecase.InsertHabitUseCase
import com.habitsapp.home.domain.home.usecase.CompleteHabitUseCase
import com.habitsapp.home.domain.home.usecase.GetAllHabitsForSelectedDateUseCase
import com.habitsapp.home.domain.home.usecase.HomeUseCases
import com.habitsapp.home.domain.repository.HomeRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDetailUseCases(repository: HomeRepository): DetailUseCases {
        return DetailUseCases(
            getHabitsByIdUseCase = GetHabitByIdUseCase(repository),
            insertHabitUseCase = InsertHabitUseCase(repository),
        )
    }

    @Provides
    @Singleton
    fun provideHabitDao(@ApplicationContext context: Context, moshi: Moshi): HomeDao {
        return Room.databaseBuilder(
            context,
            HomeDatabase::class.java,
            "habits_db"
        ).addTypeConverter(HomeTypeConverter(moshi)).build().dao
    }

    @Provides
    @Singleton
    fun provideHomeRepository(homeDao: HomeDao): HomeRepository {
        return HomeRepositoryImpl(homeDao)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}