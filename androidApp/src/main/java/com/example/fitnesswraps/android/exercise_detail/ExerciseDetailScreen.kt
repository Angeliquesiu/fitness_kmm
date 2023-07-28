package com.example.fitnesswraps.android.exercise_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fitnesswraps.android.exercise_list.ExerciseItem
import com.example.fitnesswraps.android.exercise_list.HideableSearchTextField

@Composable
fun ExerciseDetailScreen(
    exerciseId: Long,
    navController: NavController,
    viewModel: ExerciseDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val hasExerciseBeenSaved by viewModel.hasExerciseBeenSaved.collectAsState()
    
    LaunchedEffect(key1 = hasExerciseBeenSaved) {
        if(hasExerciseBeenSaved) {
            navController.popBackStack()
        }
    }

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::saveExercise,
                backgroundColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save exercise",
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TransparentHintTextField(
                text = state.exerciseTitle,
                hint = "Enter a title...",
                isHintVisible = state.isExerciseTitleHintVisible,
                onValueChanged = viewModel::onExerciseTitleChanged,
                onFocusChanged = {
                    viewModel.onExerciseTitleFocusChanged(it.isFocused)
                },
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = state.exerciseDescription,
                hint = "Enter a description...",
                isHintVisible = state.isExerciseDescriptionHintVisible,
                onValueChanged = viewModel::onExerciseDescriptionChanged,
                onFocusChanged = {
                    viewModel.onExerciseDescriptionFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier.weight(1f)
            )
        }
    }
}