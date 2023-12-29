package com.kelompok9.gethelp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.*
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.kelompok9.gethelp.db.db
import com.kelompok9.gethelp.ui.theme.GetHelpTheme
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import com.kelompok9.gethelp.ViewModel.AuthViewModel
import com.kelompok9.gethelp.ViewModel.MainViewModel
import com.kelompok9.gethelp.model.AuthModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private lateinit var  auth: FirebaseAuth;
    val viewModel by viewModels<MainViewModel>()
    fun redirectToLogin () {
        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        viewModel.getUserData(auth = auth)
        setContent {
            GetHelpTheme {
                Dashboard(viewModel = viewModel)
            }
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser == null) {
            redirectToLogin()
        }
    }
}

@Composable
fun Dashboard(viewModel: MainViewModel){
    val authViewModel = viewModel.authModel

    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds
        )
    }
    Column() {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Column(){
                Text(
                    text = "Halo, "+ viewModel.authModel.value.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    )
                )
            }
            Column(){
                Image(
                    painter = painterResource(id = R.drawable.user_view_header),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(size = 40.dp)
                        )
                )
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 71.dp, horizontal = 20.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            Box(
                modifier = Modifier
                    .width(267.dp)
                    .height(267.dp)
                    .background(
                        color = Color(0xFFFF0000),
                        shape = RoundedCornerShape(size = 267.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Text(
                            text = "Touch for help",
                            style = TextStyle(
                                fontSize = 32.sp,
                                fontWeight = FontWeight(800),
                                color = Color(0xFFFFFFFF),
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(11.dp))

                    Divider(
                        Modifier
                            .height(4.dp)
                            .background(color = Color(0xFFFFFFFF))
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Text(
                            text = "Pekanbaru, Rumbai",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                            )
                        )
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Text(
                            text = "Status: Dangerous",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                            )
                        )
                    }
                }
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp), horizontalArrangement = Arrangement.Center){
            Column(
                Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .padding(1.dp)
                    .width(159.dp)
                    .height(142.dp)
                    .background(color = Color(0xFFFFFFFF)), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Jumlah Laporan",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier.padding(top = 13.dp)
                )
                Text(
                    text = "50",
                    style = TextStyle(
                        fontSize = 64.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    )
                )
            }
            Column(
                Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .padding(1.dp)
                    .width(159.dp)
                    .height(142.dp)
                    .background(color = Color(0xFFFFFFFF)), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Jumlah Teman",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier.padding(top = 13.dp)
                )
                Text(
                    text = "0",
                    style = TextStyle(
                        fontSize = 64.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000),
                    )
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 20.dp), horizontalArrangement = Arrangement.Center){
            Button(
                onClick = {

                },
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .padding(1.dp)
                    .width(324.dp)
                    .height(70.dp)
                    .background(color = Color(0xFFFF0000), shape = RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent,
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    Text(
                        text = "Laporkan kejahatan",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(800),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color(0xFFE4E4E4))
                .width(360.dp)
                .height(83.dp)
                .background(color = Color(0xFFFFFFFF))
                .padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.home_red),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
            Image(
                painter = painterResource(id = R.drawable.user_friend),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
        }
    }
}