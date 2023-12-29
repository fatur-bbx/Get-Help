package com.kelompok9.gethelp.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.kelompok9.gethelp.db.db
import com.kelompok9.gethelp.model.AuthModel

class MainViewModel: ViewModel() {
    var authModel = mutableStateOf(AuthModel())
        private set
    fun getUserData(auth: FirebaseAuth) {
        val fireBaseQuery = db().db.collection("User").whereEqualTo("user_email",auth.currentUser?.email)
            .get()
            .addOnSuccessListener { documents ->
                for(document in documents) {
                    if (document != null) {
                        Log.d("MainActivityTag", "DocumentSnapshot data: ${document.data.get("user_name")}")
                        authModel.value = authModel.value.copy(name = document.data.get("user_name").toString())
                        authModel.value = authModel.value.copy(email = document.data.get("user_email").toString())
                        authModel.value = authModel.value.copy(inviteCode = document.data.get("user_invite_code").toString())
                    } else {
                        Log.d("MainActivityTag", "No such document")
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d("MainActivityTag", "get failed with ", exception)
            }
    }
}