package com.kelompok9.gethelp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok9.gethelp.ui.theme.GetHelpTheme

class RegisterActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetHelpTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Bg(modifier = Modifier
                        .padding(1.dp)
                        .width(45.dp)
                        .height(45.dp))
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
                    CustomTextField(label = "Name")
                    CustomTextField(label = "Email")
                    CustomTextField(label = "Password")
                    CustomTextField(label = "Confirm Password")
                    CustomButton(label = "Sign Up")
                    Text(

                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                        textAlign = TextAlign.Right,
                        text = "Already have an account?")
                }

            }
        }
    }
}