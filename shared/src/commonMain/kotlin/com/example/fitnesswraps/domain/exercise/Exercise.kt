package com.example.fitnesswraps.domain.exercise

import kotlinx.datetime.LocalDateTime

data class Exercise (
    val id: Long?,
    val title: String,
    val description: String,
    val lastEdited: LocalDateTime
)