package com.example.fitnesswraps.domain.exercise

import com.example.fitnesswraps.domain.time.DateTimeUtil

class SearchExercises {
    fun execute(exercises: List<Exercise>, query: String): List<Exercise> {
        if(query.isBlank()) {
            return exercises
        }
        return exercises.filter {
            it.title.trim().lowercase().contains(query.lowercase()) ||
                    it.description.trim().lowercase().contains(query.lowercase())
        }.sortedBy {
            DateTimeUtil.toEpochMillis(it.lastEdited)
        }
    }
}