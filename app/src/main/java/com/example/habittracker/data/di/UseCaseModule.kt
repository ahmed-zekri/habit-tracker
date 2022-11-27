package com.example.habittracker.data.di

import com.example.habittracker.domain.repositories.HabitsRepository
import com.example.habittracker.domain.use_case.AddHabitUseCase
import com.example.habittracker.domain.use_case.GetAllHabitsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationComponent (i.e. everywhere in the application)
    @Provides
    fun providesAddHabitUseCase(
       habitsRepository:HabitsRepository
    ) =  AddHabitUseCase(habitsRepository)
    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationComponent (i.e. everywhere in the application)
    @Provides
    fun providesGetHabitsUseCase(
        habitsRepository:HabitsRepository
    ) =  GetAllHabitsUseCase(habitsRepository)
}