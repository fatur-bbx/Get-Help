package com.kelompok9.gethelp.model

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.kelompok9.gethelp.db.db

data class ReportModel(
    var lokasi: String = "",
    var jenis: String = "",
    var tanggal: String = ""){
}