package com.kelompok9.gethelp.api
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

public interface LocationApi {
    @Headers(
        "Accept: application/json"
    )
    @POST("/")
    abstract fun getLocationCluster(@Body location: LocationData): Call<LocationResult>
}