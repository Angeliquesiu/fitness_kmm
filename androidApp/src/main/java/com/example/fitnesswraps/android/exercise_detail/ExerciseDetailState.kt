package com.example.fitnesswraps.android.exercise_detail

data class ExerciseDetailState(
    val exerciseTitle: String = "",
    val isExerciseTitleHintVisible: Boolean = false,
    val exerciseDescription: String = "",
    val isExerciseDescriptionHintVisible: Boolean = false
)
