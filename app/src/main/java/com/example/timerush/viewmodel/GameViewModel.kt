package com.example.timerush.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timerush.data.repository.GameRepository
import com.example.timerush.utils.QuizData
import com.example.timerush.utils.RewardCalculator
import com.example.timerush.utils.StreakPreferences
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.timerush.data.repository.QuizRepository
import com.example.timerush.utils.QuizData.questions


class GameViewModel(
    private val repository: GameRepository
) : ViewModel() {

    private val quizRepository = QuizRepository()

    init {
        viewModelScope.launch {
            val fetched = quizRepository.fetchQuestions()
            if (fetched.isNotEmpty()) {
                questions = fetched
                currentQuestion = questions[0]
            }
        }
    }

    private var index = 0
    private var isAnswered = false


    var currentQuestion by mutableStateOf(questions[index])
        private set

    var score by mutableStateOf(0)
        private set

    var isGameFinished by mutableStateOf(false)
        private set


    fun submitAnswer(answer: String) {

        if (isAnswered || isGameFinished) return

        isAnswered = true

        if (answer == currentQuestion.correctAnswer) {
            score += 10
        }

        if (index < questions.size - 1) {
            index++
            currentQuestion = questions[index]
            isAnswered = false
        } else {
            isGameFinished = true
        }
    }





    @RequiresApi(Build.VERSION_CODES.O)
    fun finishGame() {
        repository.completeQuiz(score)
    }



}


