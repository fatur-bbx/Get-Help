package com.kelompok9.gethelp.model

import android.util.Log
import androidx.compose.ui.graphics.Color
import com.google.firebase.auth.FirebaseAuth
import com.kelompok9.gethelp.db.db

data class LocationModel(
    var kelurahan: String = "",
    var status: Int = 0,
    var crimeCount: Int = 0,
    var statusColor: Long = 0,
    var statusText: String = "Very Safe") {
    fun getLocationStatus(){
        if(this.status == 4) {
            this.statusText = "Safe"
            this.statusColor = 0xFF00D1FF
        }
        if(this.status == 3) {
            this.statusText = "Medium"
            this.statusColor = 0xFFFFC700
        }
        if(this.status == 2) {
            this.statusText = "Dangerous"
            this.statusColor = 0xFFFF0000
        }
        if(this.status == 1) {
            this.statusText = "Safe"
            this.statusColor = 0xFF00D1FF
        }
        if(this.status == 0) {
            this.statusText = "Dangerous"
            this.statusColor = 0xFFFF0000
        }
    }

}