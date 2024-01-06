package com.kelompok9.gethelp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kelompok9.gethelp.ViewModel.ReportViewModel
import com.kelompok9.gethelp.db.db
import com.kelompok9.gethelp.ui.theme.GetHelpTheme
import java.text.SimpleDateFormat
import java.util.Date

class ReportActivity : ComponentActivity(){
    val viewModel by viewModels<ReportViewModel>()
    val locationOption = arrayOf("Sialang Sakti", "Sail", "Kulim", "Gobah", "Rumbai Barat")
    val crimeOption = arrayOf("Sialang Sakti", "Sail", "Kulim", "Gobah", "Rumbai Barat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                        text = "Pelaporan")
                    DropDown("Lokasi", options= locationOption, value= viewModel.reportModel.value.lokasi,viewModel = ReportViewModel(), ::onLokasiChange)
                    MyDatePickerDialog(viewModel = ReportViewModel(), ::onTanggalChange)
                    DropDown("Lokasi", options= crimeOption,value= viewModel.reportModel.value.jenis,viewModel = ReportViewModel(), ::onJenisChange)
                    CustomButton(label = "Laporkan", onClick = fun(){
                        saveData()
                    })
                }

            }
        }
    }

    fun onLokasiChange(newValue: String) {
        viewModel.reportModel.value = viewModel.reportModel.value.copy(lokasi = newValue)
    }
    fun onTanggalChange(newValue: String) {
        viewModel.reportModel.value = viewModel.reportModel.value.copy(tanggal = newValue)
    }
    fun onJenisChange(newValue: String) {
        viewModel.reportModel.value = viewModel.reportModel.value.copy(jenis = newValue)
    }
    private fun saveData() {
        var data = hashMapOf<String, Any>()
        var date = SimpleDateFormat("dd/MM/yyyy").parse(viewModel.reportModel.value.tanggal).time;
        data["lokasi"] = viewModel.reportModel.value.lokasi
        data["tanggal"] = date
        data["jenis"] = viewModel.reportModel.value.jenis
        val firebaseQuery = db().db.collection("Report").add(data)
        startActivity(Intent(this, MainActivity::class.java));
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DatePickerView() {
        val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis()
            }
        })
        val selectedDate = datePickerState.selectedDateMillis?.let {
            convertMillisToDate(it)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DatePicker(
                state = datePickerState
            )
            Spacer(
                modifier = Modifier.height(
                    32.dp
                )
            )
            Text(
                text = selectedDate.toString(),
                color = Color.Red
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyDatePickerDialog(
        onDateSelected: (String) -> Unit,
        onDismiss: () -> Unit
    ) {
        val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis()
            }
        })

        val selectedDate = datePickerState.selectedDateMillis?.let {
            convertMillisToDate(it)
        } ?: ""

        androidx.compose.material3.DatePickerDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                Button(onClick = {
                    onDateSelected(selectedDate)
                    onDismiss()
                }

                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(onClick = {
                    onDismiss()
                }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    @Composable
    fun MyDatePickerDialog(viewModel: ReportViewModel, callBack: (tanggal: String) -> Unit) {
        var date = viewModel.reportModel.value.tanggal

        var showDatePicker by remember {
            mutableStateOf(false)
        }

        Box(contentAlignment = Alignment.Center) {
            Button(onClick = { showDatePicker = true }) {
                Text(text = date)
            }
        }

        if (showDatePicker) {
            MyDatePickerDialog(
                onDateSelected = { callBack(it)},
                onDismiss = { showDatePicker = false }
            )
        }
    }


    private fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(Date(millis))
    }
}

@Composable
fun DropDown(label: String, options: Array<String>, value: String, viewModel: ReportViewModel, callback: (lokasi: String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = value,
            onValueChange = { },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(text = selectionOption) },
                    onClick = {
                        callback(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}