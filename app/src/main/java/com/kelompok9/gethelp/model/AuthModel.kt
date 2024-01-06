package com.kelompok9.gethelp.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.kelompok9.gethelp.db.db

data class AuthModel(
    var name: String = "",
    var email: String = "asdfasdfasdf",
    var password: String = "",
    var confirmPassword: String = "",
    var inviteCode: String = ""){
    fun checkPassword(): Boolean{
        return password.equals(confirmPassword)
    }


}