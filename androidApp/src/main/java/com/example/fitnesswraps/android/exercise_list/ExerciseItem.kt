package com.example.fitnesswraps.android.exercise_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.fitnesswraps.domain.exercise.Exercise
import com.example.fitnesswraps.domain.time.DateTimeUtil

@Composable
fun ExerciseItem(
    exercise: Exercise,
    backgroundColor: Color,
    onNoteClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formattedDate = remember(exercise.lastEdited) {
        DateTimeUtil.stringifyDateTime(exercise.lastEdited)
    }
}
