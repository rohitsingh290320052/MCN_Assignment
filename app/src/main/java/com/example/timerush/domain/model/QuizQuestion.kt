package com.example.timerush.domain.model

data class QuizQuestion(
    val question: String = "",
    val options: List<String> = emptyList(),
    val correctAnswer: String = ""
)
