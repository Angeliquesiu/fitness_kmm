package com.example.fitnesswraps.android.di

import android.app.Application
import com.example.fitnesswraps.data.exercise.SqlDelightExerciseDataSource
import com.example.fitnesswraps.data.local.DatabaseDriverFactory
import com.example.fitnesswraps.database.FitnessWrapsDatabase
import com.example.fitnesswraps.domain.exercise.ExerciseDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideExerciseDataSource(driver: SqlDriver): ExerciseDataSource {
        return SqlDelightExerciseDataSource(FitnessWrapsDatabase(driver))
    }
}