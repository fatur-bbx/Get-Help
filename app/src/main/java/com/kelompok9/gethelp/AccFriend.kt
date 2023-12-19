package com.kelompok9.gethelp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
fun AccFriend(){
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
                .padding(start = 20.dp, top = 34.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = "Accept Friend Request",
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
                .padding(horizontal = 20.dp, vertical = 24.dp), horizontalArrangement = Arrangement.SpaceBetween){
            Column(){
                Row(Modifier.fillMaxWidth()){
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(size = 16.dp)
                            )
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(16.dp),
                            )){
                        Row(modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 13.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxHeight()){
                                Image(
                                    painter = painterResource(id = R.drawable.user2),
                                    contentDescription = "image description",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .width(37.dp)
                                        .height(37.dp)
                                )
                                Text(
                                    text = "Lorem Ipsum",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    ),
                                    modifier = Modifier.padding(start=10.dp)
                                )
                            }
                            Row(){
                                Image(
                                    painter = painterResource(id = R.drawable.checklist),
                                    contentDescription = "image description",
                                    modifier = Modifier.padding(end=20.dp)
                                        .width(24.dp)
                                        .height(24.dp),
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.line_md_remove),
                                    contentDescription = "image description",
                                    Modifier
                                        .padding(1.dp)
                                        .width(24.dp)
                                        .height(24.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(610.dp))
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
fun BgPreview2() {
    GetHelpTheme {
        AccFriend()
    }
}