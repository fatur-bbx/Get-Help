package com.kelompok9.gethelp.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.kelompok9.gethelp.model.AuthModel
import com.kelompok9.gethelp.model.ReportModel

class ReportViewModel: ViewModel() {
    var reportModel = mutableStateOf(ReportModel())
        private set
}