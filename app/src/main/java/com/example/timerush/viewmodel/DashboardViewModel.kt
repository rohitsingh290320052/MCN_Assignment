package com.example.timerush.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class DashboardViewModel : ViewModel() {

    private val uid = FirebaseAuth.getInstance().currentUser?.uid
    private val db = FirebaseFirestore.getInstance()

    val streak = mutableStateOf(0)
    val hasPlayedToday = mutableStateOf(false)

    init {
        if (uid != null) {


        db.collection("users").document(uid)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot == null) return@addSnapshotListener

                streak.value = snapshot.getLong("streak")?.toInt() ?: 0
                hasPlayedToday.value =
                    snapshot.getString("lastPlayedDate") == LocalDate.now().toString()
            }
        }
    }
}
