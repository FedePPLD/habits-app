package com.habitsapp.home.data.repository

import com.habitsapp.home.data.extension.toStartOfDateTimestamp
import com.habitsapp.home.data.local.HomeDao
import com.habitsapp.home.data.maper.toDomain
import com.habitsapp.home.data.maper.toDto
import com.habitsapp.home.data.maper.toEntity
import com.habitsapp.home.data.remote.HomeApi
import com.habitsapp.home.data.remote.util.resultOf
import com.habitsapp.home.domain.models.Habit
import com.habitsapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.ZonedDateTime

class HomeRepositoryImpl(
    private val dao: HomeDao,
    private val api: HomeApi
) : HomeRepository {

    override fun getAllHabitsForSelectedDate(date: ZonedDateTime): Flow<List<Habit>> {
        val localFlow = dao.getAllHabitsForSelectedDate(date.toStartOfDateTimestamp()).map {
            it.map { it.toDomain() }
        }

        val apiFlow = getHabitsFromApi()


        return localFlow.combine(apiFlow) { db, _ -> db }
    }

    private fun getHabitsFromApi(): Flow<List<Habit>> {
        return flow<List<Habit>> {

            resultOf {
                val habits = api.getAllHabits().toDomain()
                insertOrUpdateHabits(habits)
            }
        }.onStart {
            emit(emptyList())
        }
    }

    override suspend fun insertOrUpdateHabit(habit: Habit) {
        dao.insertHabit(habit.toEntity())

        resultOf {
            api.insertHabit(habit.toDto())
        }
    }

    private suspend fun insertOrUpdateHabits(habits: List<Habit>) {
        dao.insertHabits(habits.map {
            it.toEntity()
        })
    }

    override suspend fun getHabitById(id: String): Habit {
        return dao.getHabitById(id).toDomain()
    }
}