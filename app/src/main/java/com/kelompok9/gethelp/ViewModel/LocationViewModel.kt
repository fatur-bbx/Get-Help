package com.kelompok9.gethelp.ViewModel

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.kelompok9.gethelp.LoginActivity
import com.kelompok9.gethelp.api.LocationApi
import com.kelompok9.gethelp.api.LocationData
import com.kelompok9.gethelp.api.LocationResult
import com.kelompok9.gethelp.db.db
import com.kelompok9.gethelp.model.AuthModel
import com.kelompok9.gethelp.model.LocationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationViewModel: ViewModel() {
    var authModel = mutableStateOf(AuthModel())
        private set
    var locationModel = mutableStateOf(LocationModel())
        private set
    private lateinit var  auth: FirebaseAuth;
    fun getUserData(auth: FirebaseAuth) {
        this.auth = auth
        val fireBaseQuery = db().db.collection("User").whereEqualTo("user_email",auth.currentUser?.email)
            .get()
            .addOnSuccessListener { documents ->
                for(document in documents) {
                    if (document != null) {
                        Log.d("MainActivityTag", "DocumentSnapshot data: ${document.data.get("user_email")}")
                        authModel.value = authModel.value.copy(name = document.data.get("user_name").toString())
                        authModel.value = authModel.value.copy(email = document.data.get("user_email").toString())
                        authModel.value = authModel.value.copy(inviteCode = document.data.get("user_invite_code").toString())
                    } else {
                        Log.d("MainActivityTag", "No such document")
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d("MainActivityTag", "get failed with ", exception)
            }
    }

    fun signOut(){
        auth.signOut();
    }

    fun getLocationStatus(wardName: String, crimeCount: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://128.199.226.237")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(LocationApi::class.java)
        val locationData = LocationData(wardName = wardName, crimeCount = crimeCount)
        val call = api.getLocationCluster(locationData);

        call!!.enqueue(object: Callback<LocationResult?> {
            override fun onResponse(call: Call<LocationResult?>, response: Response<LocationResult?>) {
                if(response.isSuccessful) {

                    if(response.body()?.cluster != null){
                        var status = response.body()?.cluster.toString()
                        locationModel.value = locationModel.value.copy(status = status.toInt())
                        locationModel.value.getLocationStatus()
                        Log.d("MainTag", "success!" + locationModel.value.statusColor)
                    }

                }
            }

            override fun onFailure(call: Call<LocationResult?>, t: Throwable) {
                Log.e("MainTag", "Failed mate " + t.message.toString())
            }
        })
    }

}