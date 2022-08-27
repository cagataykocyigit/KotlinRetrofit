package com.example.retrofitkotlin.model

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

data class CryptoModel(
   //@SerializedName("currency")
    val currency : String,

    //@SerializedName("price")
    val price : String,

    val name : String
    )

  @GET
fun getData() : Call<List<CryptoModel>>? {

    return null
}


