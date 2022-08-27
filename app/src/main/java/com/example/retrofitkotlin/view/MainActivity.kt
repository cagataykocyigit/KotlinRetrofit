package com.example.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.adapter.CustomAdapter
import com.example.retrofitkotlin.databinding.ActivityMainBinding
import com.example.retrofitkotlin.model.CryptoModel
import com.example.retrofitkotlin.service.CryptoAPI
import com.example.retrofitkotlin.viewmodel.CryptoViewmodel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityMainBinding
    val viewModel = CryptoViewmodel()
    private var itemsList = ArrayList<CryptoModel>()
    private var recyclerViewAdapter : CustomAdapter? = null

    private val BASE_URL = "https://api.nomics.com/v1/"
    private var cryptoModels : ArrayList<CryptoModel>? = null

    //Disposable(kullan at) calları işin bittiğine at
    private var compositeDisposable : CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable = CompositeDisposable()

        //RecyclerView
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager


      loadData()

    }


    fun loadData() {

        //Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)


        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))




        /*
         val service = retrofit.create(CryptoAPI::class.java)
         val call = service.getData()

        call.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        cryptoModels = ArrayList(it)

                        cryptoModels?.let {
                            recyclerViewAdapter = CustomAdapter(it)
                            binding.recyclerView.adapter = recyclerViewAdapter

                        }





                        /*
                        for (cryptoModel : CryptoModel in cryptoModels!!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                            println(cryptoModel.name)
                        }

                         */


                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {

                t.printStackTrace()
            }

        })*/

    }

    private fun handleResponse(cryptoList : List<CryptoModel>){

        cryptoModels = ArrayList(cryptoList)

        cryptoModels?.let {
            recyclerViewAdapter = CustomAdapter(it)
            binding.recyclerView.adapter = recyclerViewAdapter

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

}