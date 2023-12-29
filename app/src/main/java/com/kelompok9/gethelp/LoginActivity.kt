package com.kelompok9.gethelp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kelompok9.gethelp.ViewModel.AuthViewModel
import com.kelompok9.gethelp.ui.theme.GetHelpTheme
class LoginActivity : ComponentActivity(){
    private lateinit var  auth: FirebaseAuth;
    val viewModel by viewModels<AuthViewModel>()
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
                        text = "Sign In")
                    CustomTextField(value = viewModel.authModel.value.email ,label = "Email", onNewValue = ::onEmailChange)
                    CustomTextField(value = viewModel.authModel.value.password, label = "Password", onNewValue = ::onEmailChange)
                    CustomButton(label = "Sign In", onClick = fun(){

                    })
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        textAlign = TextAlign.Right,
                        text = "Not register yet?")
                }

            }
        }
    }

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
    fun onEmailChange(newValue: String) {
        viewModel.authModel.value = viewModel.authModel.value.copy(email = newValue)
    }

    private fun createAccount() {
        val email = viewModel.authModel.value.email
        val password = viewModel.authModel.value.password
    }
}
@Composable
fun CustomTextField(value: String, label: String, onNewValue: (String)-> Unit) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)) {
        Text(text = label)
        TextField(
            value = value,
            onValueChange = { onNewValue(it) },
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent),
            shape = RoundedCornerShape(20.dp)
        )
    }
}

@Composable
fun CustomButton(label: String, onClick: ()->Unit){
    Box(modifier = Modifier.fillMaxWidth()){
        Button(onClick = { onClick() },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.Center),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White,
            )
        ) {
            Box(modifier = Modifier
                .wrapContentHeight()){
                Text(text = label,
                    fontSize = 26.sp)
            }
        }
    }
}