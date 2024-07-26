package com.habitsapp.home.data.repository

import com.habitsapp.home.data.extension.toStartOfDateTimestamp
import com.habitsapp.home.data.local.HomeDao
import com.habitsapp.home.data.maper.toDomain
import com.habitsapp.home.data.maper.toEntity
import com.habitsapp.home.domain.models.Habit
import com.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.ZonedDateTime

class HomeRepositoryImpl(private val dao: HomeDao) : HomeRepository {

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        return dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp()).map {
            it.map {
                it.toDomain()
            }
        }
    }

    override suspend fun insertOrUpdateHabit(habit: Habit) {
        dao.insertHabit(habit.toEntity())
    }

    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id).toDomain()
    }
}