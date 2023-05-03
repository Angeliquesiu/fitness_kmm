package com.example.fitnesswraps.data.exercise

import com.example.fitnesswraps.domain.exercise.Exercise
import database.ExerciseEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun ExerciseEntity.toExercise(): Exercise {
    return Exercise(
        id = id,
        title = title,
        description = description,
        lastEdited = Instant
            .fromEpochMilliseconds(lastEdited)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}