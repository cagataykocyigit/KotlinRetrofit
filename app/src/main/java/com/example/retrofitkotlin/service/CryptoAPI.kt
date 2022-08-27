package com.example.retrofitkotlin.service

import android.telecom.Call
import com.example.retrofitkotlin.model.CryptoModel
import io.reactivex.Observable
import retrofit2.http.GET
import java.util.*

interface CryptoAPI {

    //GET , POST , UPDATE , DELETE
    // API KEY = 45f6f506d33222e8e0eeca9de7baab591ff6e236
    //https://api.nomics.com/v1/prices?key=

    /*
    @GET("currencies/ticker?key=45f6f506d33222e8e0eeca9de7baab591ff6e236")
    fun getData(): retrofit2.Call<List<CryptoModel>>

     */

    @GET("currencies/ticker?key=45f6f506d33222e8e0eeca9de7baab591ff6e236")
    fun getData(): Observable<List<CryptoModel>>

}