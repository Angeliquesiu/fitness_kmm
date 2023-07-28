package com.example.fitnesswraps.android.exercise_detail

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnesswraps.domain.exercise.Exercise
import com.example.fitnesswraps.domain.exercise.ExerciseDataSource
import com.example.fitnesswraps.domain.time.DateTimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailViewModel @Inject constructor(
    private val exerciseDataSource: ExerciseDataSource,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val exerciseTitle = savedStateHandle.getStateFlow("exerciseTitle", "")
    private val isExerciseTitleFocused = savedStateHandle.getStateFlow("isExerciseTitleFocused", false)
    private val exerciseDescription = savedStateHandle.getStateFlow("exerciseDescription", "")
    private val isExerciseDescriptionFocused = savedStateHandle.getStateFlow("isExerciseDescriptionFocused", false)

    val state = combine (
        exerciseTitle,
        isExerciseTitleFocused,
        exerciseDescription,
        isExerciseDescriptionFocused
    ) { title, isTitleFocused, description, isDescriptionFocused ->
        ExerciseDetailState(
            exerciseTitle = title,
            isExerciseTitleHintVisible = title.isEmpty() && !isTitleFocused,
            exerciseDescription = description,
            isExerciseDescriptionHintVisible = description.isEmpty() && !isDescriptionFocused
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ExerciseDetailState())

    private val _hasExerciseBeenSaved = MutableStateFlow(false)
    val hasExerciseBeenSaved = _hasExerciseBeenSaved.asStateFlow()

    private var existingExerciseId: Long? = null

    init {
        savedStateHandle.get<Long>("exerciseId")?.let {existingExerciseId ->
            if (existingExerciseId == -1L) {
                return@let // Fallback for nullable Long (When adding a new exercise)
            }
            this.existingExerciseId = existingExerciseId
            viewModelScope.launch {
                exerciseDataSource.getExerciseById(existingExerciseId)?.let { exercise ->
                    savedStateHandle["exerciseTitle"] = exercise.title
                    savedStateHandle["exerciseDescription"] = exercise.description
                }
            }
        }
    }

    fun onExerciseTitleChanged(text: String) {
        savedStateHandle["exerciseTitle"] = text
    }
    fun onExerciseDescriptionChanged(text: String) {
        savedStateHandle["exerciseDescription"] = text
    }
    fun onExerciseTitleFocusChanged(isFocused: Boolean) {
        savedStateHandle["isExerciseTitleFocused"] = isFocused
    }
    fun onExerciseDescriptionFocusChanged(isFocused: Boolean) {
        savedStateHandle["isExerciseDescriptionFocused"] = isFocused
    }

    fun saveExercise() {
        viewModelScope.launch {
            exerciseDataSource.insertExercise(
                Exercise(
                    id = existingExerciseId,
                    title = exerciseTitle.value,
                    description = exerciseDescription.value,
                    lastEdited = DateTimeUtil.now()
                )
            )
            _hasExerciseBeenSaved.value = true
        }
    }
}