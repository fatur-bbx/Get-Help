package com.kelompok9.gethelp

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.GridLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.*
import androidx.compose.ui.Alignment.Companion.BottomEnd
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok9.gethelp.ui.theme.GetHelpTheme

class MainActivity : ComponentActivity() {
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
                Header()
                BtnTFH()
                JumlahBTN()
                Nav()
                BtnLapor()
            }
        }
    }
}

@Composable
fun GetScreenSize(): Pair<Dp, Dp> {
    val context: Context = LocalContext.current
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    val density = LocalDensity.current.density

    val screenWidth = displayMetrics.widthPixels / density
    val screenHeight = displayMetrics.heightPixels / density

    return Pair(screenWidth.dp, screenHeight.dp)
}
@Composable
fun Header(modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Teks di samping kiri
        Text(
            text = "Halo, Lorem Ipsum",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )

        // Gambar berbentuk bulat di samping kanan
        Box(
            modifier = Modifier
                .size(40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_view_header),
                contentDescription = "Icon",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun BtnLapor(modifier: Modifier = Modifier){
    Box(modifier = Modifier.fillMaxSize()){
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .width(324.dp)
                .height(70.dp)
                .align(Alignment.Center)
                .offset(y = (275).dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White,
            )
        ) {
            Box(modifier = Modifier
                .height(80.dp)){
                Text(text = "Laporkan Kejahatan",
                    fontSize = 26.sp,
                    modifier = Modifier
                        .align(Alignment.Center))
            }
        }
    }
}

@Composable
fun BtnTFH(modifier: Modifier = Modifier){
    Box(modifier = Modifier.fillMaxSize()){
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
                .size(267.dp)
                .align(Alignment.Center)
                .offset(y = (-85).dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White,
                )
        ) {
            Box(modifier = Modifier
                .height(80.dp)){
                Text(text = "Touch for Help",
                        fontSize = 26.sp,
                        modifier = Modifier
                            .align(Alignment.Center))
                Spacer(modifier = Modifier
                    .width(158.dp)
                    .height(4.dp)
                    .background(color = Color.White)
                    .align(Alignment.BottomCenter))
                Text(
                    text = "Pekanbaru, Rumbai",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = (30).dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Status: Dangerous",
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = (50).dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun JumlahBTN() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .offset(y = (450).dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 30.dp,
                    spotColor = Color(0x40000000),
                    ambientColor = Color(0x40000000)
                )
                .width(159.dp)
                .height(142.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "Jumlah Laporan",
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopCenter)
            )

            Text(
                text = "50",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.Transparent)
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.Center)
                    .background(Color.Transparent)
                    .sizeIn(minWidth = 0.dp, minHeight = 0.dp)
                    .graphicsLayer(
                        scaleX = 1.5f,
                        scaleY = 1.5f
                    ),
                fontWeight = FontWeight(700),
                fontSize = 32.sp
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .shadow(
                    elevation = 30.dp,
                    spotColor = Color(0x40000000),
                    ambientColor = Color(0x40000000)
                )
                .width(159.dp)
                .height(142.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "Jumlah Teman",
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopCenter)
            )

            Text(
                text = "0",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.Transparent)
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.Center)
                    .background(Color.Transparent)
                    .sizeIn(minWidth = 0.dp, minHeight = 0.dp)
                    .graphicsLayer(
                        scaleX = 1.5f,
                        scaleY = 1.5f
                    ),
                fontWeight = FontWeight(700),
                fontSize = 32.sp
            )
        }
    }
}


@Composable
fun Bg(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Warna latar belakang putih
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun NavImage(resourceId: Int, imgDesc: String, onClick: () -> Unit = {}, modifier: Modifier = Modifier){
    Image(
        painter = painterResource(id = resourceId),
        contentDescription = imgDesc,
        contentScale = ContentScale.None,
        modifier = modifier.clickable(onClick = onClick)
    )
}

@Composable
fun Nav(modifier: Modifier = Modifier){
    val (screenWidth, screenHeight) = GetScreenSize()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 0.dp),
            ) {
                key("home") {
                    NavImage(resourceId = R.drawable.home_red, imgDesc = "Home Navigation",modifier = Modifier
                        .padding(1.dp))
                }
                key("location") {
                    NavImage(resourceId = R.drawable.location, imgDesc = "Location Navigation",modifier = Modifier
                        .padding(1.dp))
                }
                key("friend"){
                    NavImage(resourceId = R.drawable.user_friend, imgDesc = "Friend Navigation",modifier = Modifier
                        .padding(1.dp))
                }
                key("profile"){
                    NavImage(resourceId = R.drawable.profile, imgDesc = "Profile Navigation",modifier = Modifier
                        .padding(1.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BgPreview() {
    GetHelpTheme {
        Bg()
        Nav()
        Header()
        BtnTFH()
        JumlahBTN()
        BtnLapor()
    }
}