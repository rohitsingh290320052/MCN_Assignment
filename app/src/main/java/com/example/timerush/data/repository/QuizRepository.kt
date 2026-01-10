package com.example.timerush.data.repository

import com.example.timerush.domain.model.QuizQuestion
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class QuizRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun fetchQuestions(): List<QuizQuestion> =
        suspendCoroutine { continuation ->
            db.collection("quizzes")
                .limit(5) // daily limit
                .get()
                .addOnSuccessListener { snapshot ->
                    val questions = snapshot.toObjects(QuizQuestion::class.java)
                    continuation.resume(questions)
                }
                .addOnFailureListener {
                    continuation.resume(emptyList())
                }
        }
}
