package com.kelompok9.gethelp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.kelompok9.gethelp.ui.theme.GetHelpTheme
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelompok9.gethelp.db.db
import com.kelompok9.gethelp.ViewModel.AuthViewModel
import com.kelompok9.gethelp.helper.AuthHelper

class RegisterActivity : ComponentActivity(){
    private lateinit var  auth: FirebaseAuth;
    val viewModel by viewModels<AuthViewModel>()
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            redirectToHome()
        }
    }
    fun redirectToHome () {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            GetHelpTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                }
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(24.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        text = "Sign Up")
                    CustomTextField(value = viewModel.authModel.value.name ,label = "Name", onNewValue = ::onNameChange)
                    CustomTextField(value = viewModel.authModel.value.email ,label = "Email", onNewValue = ::onEmailChange)
                    CustomTextField(value = viewModel.authModel.value.password ,label = "Password", onNewValue = ::onPasswordChange)
                    CustomTextField(value = viewModel.authModel.value.confirmPassword ,label = "Password Confirmation", onNewValue = ::onConfirmPasswordChange)
                    CustomButton(label = "Sign Up", onClick = fun(){
                        createAccount()
                    })
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .clickable {
                                redirectToHome()
                            },
                        textAlign = TextAlign.Right,
                        text = "Already have an account?")
                }

            }
        }

    }
    fun onEmailChange(newValue: String) {
        viewModel.authModel.value = viewModel.authModel.value.copy(email = newValue)
    }
    fun onPasswordChange(newValue: String) {
        viewModel.authModel.value = viewModel.authModel.value.copy(password = newValue)
    }
    fun onNameChange(newValue: String) {
        viewModel.authModel.value = viewModel.authModel.value.copy(name = newValue)
    }
    fun onConfirmPasswordChange(newValue: String) {
        viewModel.authModel.value = viewModel.authModel.value.copy(confirmPassword = newValue)
    }
    private fun createAccount() {
        if (viewModel.authModel.value.checkPassword() == true) {
            val email = viewModel.authModel.value.email
            val password = viewModel.authModel.value.password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser?.email;
                        Toast.makeText(
                            baseContext,
                            user,
                            Toast.LENGTH_SHORT,
                        ).show()
                        var data = hashMapOf<String, String>()
                        data["user_email"] = viewModel.authModel.value.email
                        data["user_invite_code"] = AuthHelper().getRandomString(6);
                        data["user_name"] = viewModel.authModel.value.name
                        db().db.collection("User").add(data)
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }else{
            Toast.makeText(
                baseContext,
                "Your password is not same",
                Toast.LENGTH_SHORT
            )
        }

    }
    private fun updateUI(user: FirebaseUser?) {
    }
}