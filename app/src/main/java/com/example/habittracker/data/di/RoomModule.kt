package com.example.habittracker.data.di

import android.content.Context
import androidx.room.Room
import com.example.habittracker.data.room.HabitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationComponent (i.e. everywhere in the application)
    @Provides
    fun provideHabitDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        HabitDatabase::class.java,
        "habit"
    ).build() // The reason we can construct a database for the repo
    @Singleton
    @Provides
    fun provideYourDao(db: HabitDatabase) =
        db.getHabitDao() // The reason we can implement a Dao for the database
}