package com.example.fitnesswraps.domain.exercise

interface ExerciseDataSource {
    suspend fun insertExercise(exercise: Exercise)
    suspend fun getExerciseById(id: Long): Exercise?
    suspend fun getAllExercises(): List<Exercise>
    suspend fun deleteExerciseById(id: Long)
}