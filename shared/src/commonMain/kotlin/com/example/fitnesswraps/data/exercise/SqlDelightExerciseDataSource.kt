package com.example.fitnesswraps.data.exercise

import com.example.fitnesswraps.database.FitnessWrapsDatabase
import com.example.fitnesswraps.domain.exercise.Exercise
import com.example.fitnesswraps.domain.exercise.ExerciseDataSource
import com.example.fitnesswraps.domain.time.DateTimeUtil

class SqlDelightExerciseDataSource(db: FitnessWrapsDatabase): ExerciseDataSource {

    private val queries = db.exerciseQueries

    override suspend fun insertExercise(exercise: Exercise) {
        queries.insertExercise(
            id = exercise.id,
            title = exercise.title,
            description = exercise.description,
            lastEdited = DateTimeUtil.toEpochMillis(exercise.lastEdited)
        )
    }

    override suspend fun getExerciseById(id: Long): Exercise? {
        return queries
            .getExerciseById(id)
            .executeAsOneOrNull()
            ?.toExercise()
    }

    override suspend fun getAllExercises(): List<Exercise> {
        return queries.getAllExercises()
            .executeAsList()
            .map { it.toExercise() }
    }

    override suspend fun deleteExerciseById(id: Long) {
        queries.deleteExerciseById(id = id)
    }
}