package com.example.timerush.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    var isLoggedIn = mutableStateOf(auth.currentUser != null)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun login(name: String, email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                isLoggedIn.value = true
            }
            .addOnFailureListener {
                errorMessage.value = it.message
            }
    }

    fun signup(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->

                val user = result.user ?: return@addOnSuccessListener

                // 🔹 Save display name in Auth
                val profileUpdates = userProfileChangeRequest {
                    displayName = name
                }
                user.updateProfile(profileUpdates)

                // 🔹 Save user in Firestore
                FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(user.uid)
                    .set(
                        mapOf(
                            "name" to name,
                            "email" to email,
                            "totalPoints" to 0
                        )
                    )

                isLoggedIn.value = true
            }
            .addOnFailureListener {
                errorMessage.value = it.message
            }
    }


    fun logout() {
        auth.signOut()
        isLoggedIn.value = false
    }
}
