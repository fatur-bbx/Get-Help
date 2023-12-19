package com.kelompok9.gethelp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok9.gethelp.ui.theme.GetHelpTheme

@Composable
fun FriendCode(){
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
                .padding(horizontal = 20.dp, vertical = 34.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "Add Friend",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                )
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, end = 34.dp, start = 34.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                Box(
                    Modifier
                        .shadow(
                            elevation = 4.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        )
                        .width(320.dp)
                        .height(200.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 18.dp)
                        )){
                    Column(modifier = Modifier.fillMaxSize() ,horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(18.dp))
                        Text(
                            text = "Your Code",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF000000),
                            )
                        )
                        Divider(
                            Modifier
                                .padding(0.dp)
                                .width(128.dp)
                                .height(1.dp)
                                .background(color = Color(0xFF000000))
                        )
                        Spacer(modifier = Modifier.height(27.dp))
                        Text(
                            text = "251340",
                            style = TextStyle(
                                fontSize = 40.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFF0000),
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(81.dp))
                Box(
                    Modifier
                        .shadow(
                            elevation = 4.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        )
                        .width(321.dp)
                        .height(57.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 16.dp)
                        )) {
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp), verticalAlignment = Alignment.CenterVertically){
                        Text(
                            text = "Your Friend Code",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF1E1E1E),
                            )
                        )
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth().padding(vertical = 21.dp, horizontal = 20.dp), horizontalArrangement = Arrangement.Center){
            Button(
                onClick = { /* Handle button click here */ },
                modifier = Modifier
                    .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
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
                        text = "Send Friend Request",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(800),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(130.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 7.dp), horizontalArrangement = Arrangement.End){
            Box(modifier = Modifier
                .width(52.dp)
                .height(52.dp)
                .background(color = Color(0xFFFF0000), shape = CircleShape)){
                Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_plus),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
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
                painter = painterResource(id = R.drawable.home),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "image description",
                contentScale = ContentScale.None
            )
            Image(
                painter = painterResource(id = R.drawable.friend_red),
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

@Preview(showBackground = true)
@Composable
fun BgPreview3() {
    GetHelpTheme {
        FriendCode()
    }
}