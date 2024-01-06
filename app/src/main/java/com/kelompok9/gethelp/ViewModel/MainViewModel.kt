package com.kelompok9.gethelp.ViewModel

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.AggregateSource
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
import java.text.SimpleDateFormat
import java.time.LocalDate

class MainViewModel: ViewModel() {
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

    fun getLocationStatus(wardName: String) {
        var date = SimpleDateFormat("dd-MM-yyyy").parse(LocalDate.now().minusDays(30).toString()).time;
        Log.d("mainTag", date.toString())
        db().db.collection("Report").whereGreaterThanOrEqualTo("tanggal", date).count().get(AggregateSource.SERVER).addOnCompleteListener() {
            task->if(task.isSuccessful) {
                // Count fetched successfully
                val snapshot = task.result
                Log.d("mainTag", "Count: ${snapshot.count}")
            val retrofit = Retrofit.Builder()
                .baseUrl("http://128.199.226.237")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(LocationApi::class.java)
            val locationData = LocationData(wardName = wardName, crimeCount = snapshot.count.toInt())
            val call = api.getLocationCluster(locationData);

            call!!.enqueue(object: Callback<LocationResult?> {
                override fun onResponse(call: Call<LocationResult?>, response: Response<LocationResult?>) {
                    if(response.isSuccessful) {

                        if(response.body()?.cluster != null){
                            var status = response.body()?.cluster.toString()
                            locationModel.value = locationModel.value.copy(status = status.toInt())
                            locationModel.value = locationModel.value.copy(crimeCount = snapshot.count.toInt())
                            locationModel.value.getLocationStatus()
                            Log.d("MainTag", "success!" + locationModel.value.statusColor)
                        }

                    }
                }

                override fun onFailure(call: Call<LocationResult?>, t: Throwable) {
                    Log.e("MainTag", "Failed mate " + t.message.toString())
                }
            })
            } else {
                Log.d("mainTag", "Count failed: ", task.getException())
            }
        }


    }

}