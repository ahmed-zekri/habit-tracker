package com.example.habittracker.data.di

import com.example.habittracker.data.repositories.HabitRepositoryImpl
import com.example.habittracker.data.room.HabitDAO
import com.example.habittracker.domain.repositories.HabitsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {
    @Singleton
    @Provides
    fun providesHabitsRepositories(habitDAO: HabitDAO):HabitsRepository = HabitRepositoryImpl(habitDAO)
}