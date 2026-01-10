package com.example.timerush.utils

import com.example.timerush.domain.model.QuizQuestion

object QuizData {

    val questions: List<QuizQuestion> = listOf(
        QuizQuestion(
            question = "What does MVVM stand for?",
            options = listOf(
                "Model View ViewModel",
                "Main View Model",
                "Model Virtual View"
            ),
            correctAnswer = "Model View ViewModel"
        ),
        QuizQuestion(
            question = "Which database is used here?",
            options = listOf(
                "Firebase",
                "Room",
                "SQLite"
            ),
            correctAnswer = "Room"
        )
    )
}

