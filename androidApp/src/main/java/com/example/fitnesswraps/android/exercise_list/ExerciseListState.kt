package com.example.fitnesswraps.android.exercise_list

import com.example.fitnesswraps.domain.exercise.Exercise

data class ExerciseListState(
    val exercises: List<Exercise> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)
