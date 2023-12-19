package com.kelompok9.gethelp

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok9.gethelp.ui.theme.GetHelpTheme

class LocationActivity: ComponentActivity() {
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
                    verticalArrangement = Arrangement.Top
                ) {
                    SearchBar(label = "Enter location to search")
                    LocationCard(location = "Pekanbaru, Kulim", status = "Medium", case = 10)
                    LocationCard(location = "Pekanbaru, Kulim", status = "Medium", case = 10)
                    LocationCard(location = "Pekanbaru, Kulim", status = "Medium", case = 10)
                }

            }
        }
    }
}

@Composable
fun SearchBar(label: String) {
    var text = remember { mutableStateOf(TextFieldValue()) }

    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp)),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent),
        shape = RoundedCornerShape(20.dp),
        trailingIcon = {
            androidx.compose.material3.Icon(imageVector = Icons.Default.Search, contentDescription = null,)
        }
    )

}

@Composable
fun LocationCard(location: String, status: String, case: Int) {

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(top=10.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.Red)
    ) {
        Column(
            Modifier.padding(10.dp)
        ) {
            Text(text = location, color = Color.White, fontWeight = FontWeight.Bold)
            Divider(color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 24.dp)
            ) {
                Text(text = status, color = Color.White , fontWeight = FontWeight.Bold)
                Text(text = case.toString() + "x", color = Color.White , fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
        }

    }
}
